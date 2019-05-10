package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class UI {
        public static void main(String[] args) {
        Logic logic = new Logic();
        ArrayList<Integer> checkWhereWeSetBombsList = logic.getCheckWhereWeSetBombsList();
        ArrayList<Integer> freeCellsList = logic.getFreeCellsList();
        ArrayList<Integer> valueNearBombListPosition = logic.getValueNearBombListPosition();
        ArrayList<Integer> valueNearBombListValue = logic.getValueNearBombListValue();

        JFrame myJframe = new JFrame("MyMinesweeper");
        myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJframe.setSize(420, 445);

        Container container = myJframe.getContentPane();

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(9,9));
        container.add(jp1, BorderLayout.CENTER);


        ImageIcon freeBut = new ImageIcon( "C:\\Users\\Desktop\\1.png" ) ;
        ImageIcon bombBut = new ImageIcon( "C:\\Users\\Desktop\\2.png" ) ;

        ArrayList<JButton> list = new ArrayList<>();

        //=========================================================================================================
        ActionListener butLisBomb = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton butInButtonsListThatWePressed = (JButton)e.getSource();
                if (list.contains(butInButtonsListThatWePressed)){

                    int tempPressedButton = list.indexOf(butInButtonsListThatWePressed) + 1; //added to button + 1
                    System.out.println(list.indexOf(butInButtonsListThatWePressed));
                    System.out.println(tempPressedButton);

                    //explosion
                    if (checkWhereWeSetBombsList.contains(tempPressedButton)){
                       butInButtonsListThatWePressed.setIcon(new ImageIcon("C:\\Users\\Desktop\\2.png"));
                       for (int f = 0 ; f<checkWhereWeSetBombsList.size(); f++){
                                // add Tread...
                           tempPressedButton = checkWhereWeSetBombsList.get(f);
                           JButton tempPressedButtonForExplosion = list.get(tempPressedButton-1);
                           tempPressedButtonForExplosion.setIcon(new ImageIcon("C:\\Users\\Desktop\\2.png"));
                       }
                    }

                    if (freeCellsList.contains(tempPressedButton)){
                        for (int f = 0 ; f<freeCellsList.size(); f++){
                            tempPressedButton = freeCellsList.get(f);
                            JButton tempPressedButtonForExplosion = list.get(tempPressedButton-1);
                            tempPressedButtonForExplosion.setIcon(new ImageIcon("C:\\Users\\Desktop\\4.png"));
                        }
                    }

                    if (valueNearBombListPosition.contains(tempPressedButton)){
                        System.out.println("===="+valueNearBombListPosition.indexOf(tempPressedButton));
                        butInButtonsListThatWePressed.setIcon(new ImageIcon("C:\\Users\\Desktop\\4.png"));
                        butInButtonsListThatWePressed.setText(Integer.toString(valueNearBombListValue.get(valueNearBombListPosition.indexOf(tempPressedButton))));

                    }

                }
           }
        };

        ActionListener butStartAL = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    myJframe.dispose();
                    main(args);
                }
            };

        //=========================================================================================================

        for (int i = 0 ; i<81 ; i++) {
            JButton tempBut = new JButton(freeBut);
            tempBut.addActionListener(butLisBomb);
            list.add(tempBut);
        }

        for (int i = 0 ; i<81 ; i++) {
            jp1.add(list.get(i), i);
        }
        //=========================================================================================================
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1,2));
        container.add(jp2, BorderLayout.SOUTH);

        JButton newGame = new JButton("New Game");
        jp2.add(newGame);

        newGame.addActionListener(butStartAL);



        myJframe.setVisible(true);
    }
}
