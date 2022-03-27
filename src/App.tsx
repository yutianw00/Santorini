import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"

var SETUP = 0;
var CHOOSEMOVE = 1;
var MOVE = 2;
var BUILD = 4;
var FINISH = 5;
var USEPOWER = 6;

interface Cell {
  levels: number;
  player: number;
  pos: String;
  text: String;
  chosen: number
}

interface GameCells {
  cells: Array<Cell>,
  template: HandlebarsTemplateDelegate<any>,
  nextPlayer: number,
  nextMove: number,
  linkheader: String,
  showError: boolean,
  instruction: String,
  finish: boolean,

  selectGod: boolean,
  selectGodError: boolean,

  god1msg: String,
  god2msg: String,

  canUse1: boolean,
  canUse2: boolean,

}

interface Props {
}

class App extends Component<Props, GameCells> {
  constructor(props: Props) {
    super(props);
    this.state = {
      cells: [
        { levels: 0, player: 0, pos: "x=0&y=0", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=0&y=1", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=0&y=2", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=0&y=3", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=0&y=4", text: "0", chosen: 0 },

        { levels: 0, player: 0, pos: "x=1&y=0", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=1&y=1", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=1&y=2", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=1&y=3", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=1&y=4", text: "0", chosen: 0 },

        { levels: 0, player: 0, pos: "x=2&y=0", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=2&y=1", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=2&y=2", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=2&y=3", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=2&y=4", text: "0", chosen: 0 },

        { levels: 0, player: 0, pos: "x=3&y=0", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=3&y=1", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=3&y=2", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=3&y=3", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=3&y=4", text: "0", chosen: 0 },

        { levels: 0, player: 0, pos: "x=4&y=0", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=4&y=1", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=4&y=2", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=4&y=3", text: "0", chosen: 0 },
        { levels: 0, player: 0, pos: "x=4&y=4", text: "0", chosen: 0 },
      ],
      showError: false,
      nextPlayer: 1,
      nextMove: 0,
      template: this.loadTemplate(),
      linkheader: "setup?",
      instruction: "choose the position of your worker",
      finish: false,

      selectGod: false,
      selectGodError: false,

      god1msg: "No God Mode",
      god2msg: "No God Mode",

      canUse1: false,
      canUse2: false,

    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("handlebars");
    return Handlebars.compile(src?.innerHTML, {});
  }

  /* modified */ 
  convertToCell(p: any, playerId: number): Array<Cell> {
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
      var chosenVal = p[i]["chosen"] === 1 ? playerId : 0; 
      
      var c: Cell = {
        levels: gridLevels,
        player: gridPlayer,
        pos: "x=" + x + "&y=" + y,
        text: gridText,
        chosen: chosenVal
      };
      newCells.push(c);
    }

    return newCells;
  }

  /* new */
  async newGame() {
    const href = "newgame";
    console.log(href);
    await fetch(href);
  }

  /* new */
  async selectGod(err: boolean) {
    console.log("entered");
    this.setState({selectGod: true})
    if (err) {
      this.setState({selectGodError: true})
    }
  }

  /* new */
  async newGodGame(url: String) {
    const params = (url.split("?")[1]).split("&");
    var godName1 = params[0].split("=")[1];
    var godName2 = params[1].split("=")[1];
    if (godName1 === godName2) {
      this.selectGod(true);
    } else {
      const href = "newgodgame?" + url.split("?")[1];
      console.log(href);
      await fetch(href);
      this.setState({ god1msg: godName1 })
      this.setState({ god2msg: godName2 })

    }
    
  }

  async play(url: String) {
    const href = "play?" + url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    console.log(json);

    const newCells: Array<Cell> = this.convertToCell(json, 0); // TOFIX
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
  async setAllStates(json: any) {
    console.log(json);

    if (json["status"] !== 0) {
      this.setState({showError: true});
    } else {
      this.setState({showError: false});
    }

    var playerId = json["playerId"];
    var nextAction = json["nextAction"];

    const newCells: Array<Cell> = this.convertToCell(json["board"], playerId);
    this.setState({ cells: newCells });
    this.setState({ nextPlayer: playerId});
    this.setState({ nextMove: nextAction});
    this.setState({ finish: false});

    if (nextAction === SETUP) {
      this.setState({ linkheader: "setup?"});
      this.setState({ instruction: "choose the position of your worker"});
    } else if (nextAction === CHOOSEMOVE) {
      this.setState({ linkheader: "choosemove?"});
      this.setState({ instruction: "select one of your workers to move"});
    } else if (nextAction === MOVE) {
      this.setState({ linkheader: "move?"});
      this.setState({ instruction: "select the target position of your move"});
    } else if (nextAction === BUILD) {
      this.setState({ linkheader: "build?"});
      this.setState({ instruction: "select the target position of your build"});
    } else if (nextAction === FINISH) {
      this.setState({ linkheader: ""});
      this.setState({ instruction: "YOU WON!"});
      this.setState({ finish: true});
    } else if (nextAction === USEPOWER) {
      this.setState({ linkheader: "usepoweraction?"});
      this.setState({ instruction: "Player Using God Power..."});
    } else {
      console.log("err: nextAction not specified or unexpected value!")
    }

    if (json.hasOwnProperty('god1')) {
      this.setState({ god1msg: json["god1"]});
    }
    if (json.hasOwnProperty('god2')) {
      this.setState({ god2msg: json["god2"]});
    }

    if (json.hasOwnProperty('canUse1')) {
      this.setState({ canUse1: json["canUse1"]});
    }
    if (json.hasOwnProperty('canUse2')) {
      this.setState({ canUse2: json["canUse2"]});
    }


  }

  /* new */
  async setUp(url: String) {
    const href = "setup?" + url.split("?")[1];
    console.log(href);
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);

  }

  /* new */
  async chooseMove(url: String) {
    const href = "choosemove?" + url.split("?")[1];
    console.log(href);
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);

  }

  /* new */
  async move(url: String) {
    const href = "move?" + url.split("?")[1];
    console.log(href);
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);

  }

  /* new */
  async build(url: String) {
    const href = "build?" + url.split("?")[1];
    console.log(href);
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);

  }

  /* new */
  async preparePower1() {
    const href = "preparepower1"
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);
  }

  /* new */
  async preparePower2() {
    const href = "preparepower2"
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);
  }

  /* new */
  async usePower(url: String) {
    const href = "usepower?" + url.split("?")[1];
    console.log(href);
    const response = await fetch(href);
    const json = await response.json();

    this.setAllStates(json);

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
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/choosemove" &&
      oldHref !== window.location.href
    ) {
      this.chooseMove(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/move" &&
      oldHref !== window.location.href
    ) {
      this.move(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/build" &&
      oldHref !== window.location.href
    ) {
      this.build(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/newgame" &&
      oldHref !== window.location.href
    ) {
      this.newGame();
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/newgodgame" &&
      oldHref !== window.location.href
    ) {
      console.log("new god game!");
      this.selectGod(false);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/startGodGame" &&
      oldHref !== window.location.href
    ) {
      this.newGodGame(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/usepower1" &&
      oldHref !== window.location.href
    ) {
      this.preparePower1();
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/usepower2" &&
      oldHref !== window.location.href
    ) {
      this.preparePower2();
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/usepoweraction" &&
      oldHref !== window.location.href
    ) {
      this.usePower(window.location.href);
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
              showError: this.state.showError ? "error" : "noerror",
              instruction: this.state.instruction,
              player: this.state.nextPlayer === 1 ? "player1" : "player2",
              finish: this.state.finish,

              selectGod: this.state.selectGod ? "appear" : "disappear",
              selectGodError: this.state.selectGodError ? "appear" : "disappear",

              god1msg: this.state.god1msg,
              god2msg: this.state.god2msg,

              canUse1: this.state.canUse1 ? "appear" : "disappear",
              canUse2: this.state.canUse2 ? "appear" : "disappear",

            }), 
          }}
        />
      </div>
    )
  };
};

export default App;
