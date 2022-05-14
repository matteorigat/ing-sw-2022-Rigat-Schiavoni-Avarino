package it.polimi.ingsw.view;

import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.player.MoveMessage;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.ClientConnection;
import it.polimi.ingsw.utils.gameMessage;


public class RemoteView extends View {

    private ClientConnection clientConnection;

    private int phaseCounter = 0;

    private class MessageReceiver implements Observer<String> {

        @Override
        public void update(String message) {
            System.out.println("Received: " + message);
            System.out.println("Phase: " + phaseCounter);
            try{
                String[] inputs;
                if(message.length() > 2){
                    inputs = message.split(",");
                    if(inputs[0].equals("100")) {
                        String[] inputs2 = new String[inputs.length-1];
                        for (int i = 0; i < inputs.length-1; i++)
                            inputs2[i] = inputs[i+1];
                        inputs = inputs2;
                        phaseCounter = 100;
                    }
                } else {
                    inputs = new String[1];
                    inputs[0] = message;
                }

                int[] input = new int[inputs.length];
                for(int i=0; i<inputs.length; i++){
                    input[i] = Integer.parseInt(inputs[i]);
                    if(input[i] < 0){
                        clientConnection.asyncSend("\u001B[5;31mError! Write the move well\u001B[0m");
                        return;
                    }
                }

                switch (phaseCounter){
                    case 0:
                    case 2:
                    case 4:
                    case 3: {
                        if(inputs.length == 1)
                            handleMove(phaseCounter, input[0]);
                        else clientConnection.asyncSend("\u001B[5;31mError! Write the move well\u001B[0m");
                        break;
                    }
                    case 1:{
                        if(inputs.length == 1)
                            handleMove(phaseCounter, input[0]);
                        else if(inputs.length == 2)
                            handleMove(phaseCounter, input[0], input[1]);
                        else clientConnection.asyncSend("\u001B[5;31mError! Write the move well\u001B[0m");
                        break;
                    }
                    case 100:{
                        if(inputs.length == 1)
                            handleMove(phaseCounter, input[0]);
                        else if(inputs.length == 2)
                            handleMove(phaseCounter, input[0], input[1]);
                        else if(inputs.length == 3)
                            handleMove(phaseCounter, input[0], input[1], input[2]);
                        else if(inputs.length == 5)
                            handleMove(phaseCounter, input[0], input[1], input[2], input[3], input[4]);
                        else if(inputs.length == 7)
                            handleMove(phaseCounter, input[0], input[1], input[2], input[3], input[4], input[5], input[6]);
                        else clientConnection.asyncSend("\u001B[5;31mError! Write the move well\u001B[0m");
                        break;
                    }
                }
            }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                clientConnection.asyncSend("\u001B[5;31mError! Write the move well\u001B[0m");
            }
        }

    }


    public RemoteView(Player player, String opponent, ClientConnection c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new MessageReceiver());
        c.asyncSend("Your opponent is: " + opponent);

    }

    @Override
    protected void showMessage(Object message) {
        clientConnection.asyncSend(message);
    }

    @Override
    public void update(MoveMessage message){
        showMessage(message.getModel());
        phaseCounter = message.getModel().getCurrentPhase().ordinal();
        String resultMsg = "";

        if(message.getModel().getCurrentPlayer() == getPlayer().getIndex()){
            resultMsg = gameMessage.moveMessage;
        }
        else{
            resultMsg = gameMessage.waitMessage;
        }

        if(message.getModel().getCurrentPhase().equals(GamePhase.GameEnded) && message.getModel().getWinner().getIndex() == getPlayer().getIndex()){
            resultMsg = gameMessage.winMessage;
        }
        else if(message.getModel().getCurrentPhase().equals(GamePhase.GameEnded)){
            resultMsg = gameMessage.loseMessage + getPlayer().getNickname().toUpperCase();
        }

        showMessage(resultMsg);

    }

}
