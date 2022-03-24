import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"

interface Cell {
  levels: number;
  player: number;
  pos: String;
}

interface Cells {
  cells: Array<Cell>,
  template: HandlebarsTemplateDelegate<any>,
  nextPlayer: number,
  nextMove: number
}

interface Props {
}

class App extends Component<Props, Cells> {
  constructor(props: Props) {
    super(props);
    this.state = {
      cells: [
        { levels: 0, player: 0, pos: "?x=0&y=0" },
        { levels: 0, player: 0, pos: "?x=1&y=0" },
        { levels: 0, player: 0, pos: "?x=2&y=0" },
        { levels: 0, player: 0, pos: "?x=3&y=0" },
        { levels: 0, player: 0, pos: "?x=4&y=0" },

        { levels: 0, player: 0, pos: "?x=0&y=1" },
        { levels: 0, player: 0, pos: "?x=1&y=1" },
        { levels: 0, player: 0, pos: "?x=2&y=1" },
        { levels: 0, player: 0, pos: "?x=3&y=1" },
        { levels: 0, player: 0, pos: "?x=4&y=1" },

        { levels: 0, player: 0, pos: "?x=0&y=2" },
        { levels: 0, player: 0, pos: "?x=1&y=2" },
        { levels: 0, player: 0, pos: "?x=2&y=2" },
        { levels: 0, player: 0, pos: "?x=3&y=2" },
        { levels: 0, player: 0, pos: "?x=4&y=2" },

        { levels: 0, player: 0, pos: "?x=0&y=3" },
        { levels: 0, player: 0, pos: "?x=1&y=3" },
        { levels: 0, player: 0, pos: "?x=2&y=3" },
        { levels: 0, player: 0, pos: "?x=3&y=3" },
        { levels: 0, player: 0, pos: "?x=4&y=3" },

        { levels: 0, player: 0, pos: "?x=0&y=4" },
        { levels: 0, player: 0, pos: "?x=1&y=4" },
        { levels: 0, player: 0, pos: "?x=2&y=4" },
        { levels: 0, player: 0, pos: "?x=3&y=4" },
        { levels: 0, player: 0, pos: "?x=4&y=4" },
      ],
      nextPlayer: 1,
      nextMove: 0,
      template: this.loadTemplate(),
      
    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("handlebars");
    return Handlebars.compile(src?.innerHTML, {});
  }

  /* modified */ 
  convertToCell(p: any): Array<Cell> {
    // TODO
    // var NUMROWS: number = 5;
    var NUMCOLS: number = 5;
    const newCells: Array<Cell> = [];
    for (var i = 0; i < p["cells"].length; i++) {
      var x: number = i / NUMCOLS;
      var y: number = i % NUMCOLS;
      var c: Cell = {
        levels: p[i]["levels"],
        player: p[i]["player"],
        pos: "?x=" + x + "&y=" + y
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

    console.log("here");

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

    const newCells: Array<Cell> = this.convertToCell(json["board"]);
    this.setState({ cells: newCells });
    this.setState({ nextPlayer: json["playerId"]});
    this.setState({ nextMove: json["nextAction"]});

  }

  /* new */
  async step() {
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
    this.testAPI();
    return (
    
      <div className="App">
        <div
          dangerouslySetInnerHTML={{
            __html: this.state.template({ cells: this.state.cells }),
          }}
        />
      </div>
    )
  };
};

export default App;
