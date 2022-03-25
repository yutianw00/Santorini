import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"

interface Cell {
  levels: number;
  player: number;
  pos: String;
  text: String;
}

interface GameCells {
  cells: Array<Cell>,
  template: HandlebarsTemplateDelegate<any>,
  nextPlayer: number,
  nextMove: number,
  linkheader: String,
  showError: boolean
}

interface Props {
}

class App extends Component<Props, GameCells> {
  constructor(props: Props) {
    super(props);
    this.state = {
      cells: [
        { levels: 0, player: 0, pos: "?x=0&y=0", text: "" },
        { levels: 0, player: 0, pos: "?x=1&y=0", text: "" },
        { levels: 0, player: 0, pos: "?x=2&y=0", text: "" },
        { levels: 0, player: 0, pos: "?x=3&y=0", text: "" },
        { levels: 0, player: 0, pos: "?x=4&y=0", text: "" },

        { levels: 0, player: 0, pos: "?x=0&y=1", text: "" },
        { levels: 0, player: 0, pos: "?x=1&y=1", text: "" },
        { levels: 0, player: 0, pos: "?x=2&y=1", text: "" },
        { levels: 0, player: 0, pos: "?x=3&y=1", text: "" },
        { levels: 0, player: 0, pos: "?x=4&y=1", text: "" },

        { levels: 0, player: 0, pos: "?x=0&y=2", text: "" },
        { levels: 0, player: 0, pos: "?x=1&y=2", text: "" },
        { levels: 0, player: 0, pos: "?x=2&y=2", text: "" },
        { levels: 0, player: 0, pos: "?x=3&y=2", text: "" },
        { levels: 0, player: 0, pos: "?x=4&y=2", text: "" },

        { levels: 0, player: 0, pos: "?x=0&y=3", text: "" },
        { levels: 0, player: 0, pos: "?x=1&y=3", text: "" },
        { levels: 0, player: 0, pos: "?x=2&y=3", text: "" },
        { levels: 0, player: 0, pos: "?x=3&y=3", text: "" },
        { levels: 0, player: 0, pos: "?x=4&y=3", text: "" },

        { levels: 0, player: 0, pos: "?x=0&y=4", text: "" },
        { levels: 0, player: 0, pos: "?x=1&y=4", text: "" },
        { levels: 0, player: 0, pos: "?x=2&y=4", text: "" },
        { levels: 0, player: 0, pos: "?x=3&y=4", text: "" },
        { levels: 0, player: 0, pos: "?x=4&y=4", text: "" },
      ],
      showError: false,
      nextPlayer: 1,
      nextMove: 0,
      template: this.loadTemplate(),
      linkheader: "setup",
    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("handlebars");
    return Handlebars.compile(src?.innerHTML, {});
  }

  /* modified */ 
  convertToCell(p: any): Array<Cell> {
    var NUMCOLS: number = 5;
    const newCells: Array<Cell> = [];
    for (var i = 0; i < p.length; i++) {
      var x: number = Math.floor(i / NUMCOLS);
      var y: number = i % NUMCOLS;
      
      var gridLevels: number = p[i]["levels"];
      var gridPlayer: number = p[i]["player"];

      var textLeft: String = "";
      var textRight: String = "";
      if (gridPlayer === 1) {
        textLeft = "(";
        textRight = ")";
      } else if (gridPlayer === 2) {
        textLeft = "[";
        textRight = "]";
      } 
      var gridText = textLeft + gridLevels.toString() + textRight;
      
      var c: Cell = {
        levels: gridLevels,
        player: gridPlayer,
        pos: "?x=" + x + "&y=" + y,
        text: gridText
      };
      newCells.push(c);
    }

    return newCells;
  }

  async newGame() {
    const response = await fetch("newgame");
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells });
  }

  async play(url: String) {
    const href = "play?" + url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    console.log(json);

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells });
  }

  async switch() {
    if (
      window.location.href === "http://localhost:3000/newgame" &&
      oldHref !== window.location.href
    ) {
      this.newGame();
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/play" &&
      oldHref !== window.location.href
    ) {
      this.play(window.location.href);
      oldHref = window.location.href;
    }
  };

  /* new */
  async testAPI() {
    console.log("testing API connection...");
    const response = await fetch("test");
    const json = await response.json();
    console.log(json);
  };

  /* new */
  async setUp(url: String) {
    const href = "setup?" + url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    console.log(json);

    if (json["status"] !== "0") {
      this.setState({showError: true});
    } else {
      this.setState({showError: false});
    }

    const newCells: Array<Cell> = this.convertToCell(json["board"]);
    this.setState({ cells: newCells });
    this.setState({ nextPlayer: json["playerId"]});
    this.setState({ nextMove: json["nextAction"]});
  }

  /* new */
  async step() {
    // this.setState({showError: false});
    if (
      window.location.href.split("?")[0] === "http://localhost:3000/setup" &&
      oldHref !== window.location.href
    ) {
      this.setUp(window.location.href);
      oldHref = window.location.href;
    } 
  }

  render() {
    // this.switch();
    // this.testAPI();
    this.step()
    return (
    
      <div className="App">
        <div
          dangerouslySetInnerHTML={{
            __html: this.state.template({ 
              cells: this.state.cells, 
              linkheader: this.state.linkheader, 
              showError: this.state.showError ? "error" : "noerror" }),
          }}
        />
      </div>
    )
  };
};

export default App;
