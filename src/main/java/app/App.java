package app;

import java.io.IOException;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;
import game.Game;
import game.impl.GameImpl;
import game.utils.Pos;
import game.utils.State;

public class App extends NanoHTTPD {

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private Game game;

    private int setWorkerId = 1;

    public App() throws IOException {
        super(8080);

        this.game = new GameImpl();

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning!\n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        if (uri.equals("/test")) {
            System.out.println("Testing connection: server side received!");
            String myjsonstr = "{ \"test1\": \"" + 1 + "\"," +
                    " \"test2\": \"" + 2 + "\"," +
                    " \"test3\": \"" + 3 + "\"}";

            return newFixedLengthResponse(myjsonstr);
        } else if (uri.equals("/setup")) {
            System.out.println("App: Setup request received!");
            System.out.println("uri" + uri);
            System.out.println(params.get("x"));
            System.out.println(params.get("y"));
            System.out.println("workerId:" + setWorkerId);

            int x = Integer.valueOf(params.get("x"));
            int y = Integer.valueOf(params.get("y"));
            int playerId = game.getNextPlayerId();
            System.out.println("playerId:" + playerId);

            State state = game.setWorker(playerId, setWorkerId, new Pos(x, y));
            if (state == null) {
                return newFixedLengthResponse(new State(game.getCurrState(), -1).toString());
            }
            this.setWorkerId = (this.setWorkerId == 1) ? 2 : 1;
            return newFixedLengthResponse(state.toString());
        } else if (uri.equals("/choosemove")) {
            System.out.println("App: chooseMove request received!");
            System.out.println("uri" + uri);
            System.out.println(params.get("x"));
            System.out.println(params.get("y"));

            int x = Integer.valueOf(params.get("x"));
            int y = Integer.valueOf(params.get("y"));

            State state = game.chooseMove(new Pos(x, y));
            setWorkerId = game.getChosenWorkerId();

            if (state == null) {
                return newFixedLengthResponse(new State(game.getCurrState(), -1).toString());
            }
            return newFixedLengthResponse(state.toString());
        } else if (uri.equals("/move")) {
            System.out.println("App: move request received!");
            System.out.println("uri" + uri);
            System.out.println(params.get("x"));
            System.out.println(params.get("y"));

            int x = Integer.valueOf(params.get("x"));
            int y = Integer.valueOf(params.get("y"));
            int playerId = game.getNextPlayerId();

            State state = game.move(playerId, setWorkerId, new Pos(x, y));
            if (state == null) {
                return newFixedLengthResponse(new State(game.getCurrState(), -1).toString());
            }
            return newFixedLengthResponse(state.toString());
        }
        return null;
//        if (uri.equals("/newgame")) {
//            this.game = new Game();
//        } else if (uri.equals("/play")) {
//            this.game = this.game.play(Integer.parseInt(params.get("x")), Integer.parseInt(params.get("y")));
//        } else if (uri.equals("/undo")) {
//            this.game = this.game.undo();
//        }
//        // Extract the view-specific data from the game and apply it to the template.
//        GameState gameplay = GameState.forGame(this.game);
//        return newFixedLengthResponse(gameplay.toString());
    }

    public static class Test{
        public String getText() {
            return "Hello World!";
        }
    }
}