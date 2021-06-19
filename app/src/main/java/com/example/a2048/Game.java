package com.example.a2048;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Game
{
    // button IDs
    private int[][] IDs = {{R.id.button1 , R.id.button2 , R.id.button3 , R.id.button4} ,
            {R.id.button5 , R.id.button6 , R.id.button7 , R.id.button8} ,
            {R.id.button9 , R.id.button10 , R.id.button11 , R.id.button12} ,
            {R.id.button13 , R.id.button14 , R.id.button15 , R.id.button16}};

    // arrays
    private ImageView[][] imageViewsNumbers;
    private int intArrayCards[][];

    // activity and views
    private Activity activity;
    private TextView textViewScore;

    // listener
    private OnSwipeTouchListener onSwipeTouchListener;

    private int highScore = 0;

    // Objects
    private Swipe swipe;
    private CardsController cardsController;
    private DataHelper dataHelper;
    private Moving moving;

    private int row , column;



    public Game(Activity activity)
    {
        this.activity = activity;

        initializeImageViewsNumbers();
        initializeIntArrayCards();
        initializeTextView();
        initializeSwipeTouchListener();

        initializeObjects();
    }

    public void startGame()
    {
        cardsController.insertCardNumberRandomly();
    }

    // 1 initialization
    private void initializeImageViewsNumbers()
    {
        initializeImageViewNumbersArray();
        referenceAndSetImageButtons();

    }

    private void initializeImageViewNumbersArray() {
        imageViewsNumbers = new ImageView[4][4];
    }

    private void referenceAndSetImageButtons()
    {
        for(int i = 0; i < imageViewsNumbers.length; ++i)
            for(int j = 0; j < imageViewsNumbers[0].length; ++j)
                connectImageViewAndSetImageValue(i , j);
    }

    private void connectImageViewAndSetImageValue(int i , int j)
    {
        imageViewsNumbers[i][j] = activity.findViewById(IDs[i][j]);
        imageViewsNumbers[i][j].setImageResource(R.drawable.blank);
    }


    // 2 initialization
    private void initializeIntArrayCards()
    {
        createIntArrayCards();
        setIntArrayCardsElementsToZero();
        initializeTempIntArray();
    }

    private void createIntArrayCards()
    {
        intArrayCards = new int[4][4];
    }

    private void setIntArrayCardsElementsToZero()
    {
        for(int i = 0; i < intArrayCards.length; ++i)
            for(int j = 0; j < intArrayCards[i].length; ++j)
                intArrayCards[i][j] = 0;
    }

    private void initializeTempIntArray()
    {
        tempIntArray = new int[4][4];
    }


    // 3 initialization
    private void initializeTextView()
    {
        this.textViewScore = activity.findViewById(R.id.textViewScoreNumber);
    }


    // 4 initialization
    private void initializeSwipeTouchListener()
    {
        onSwipeTouchListener = new OnSwipeTouchListener(activity, activity.findViewById(R.id.gridLayoutGame));
        onSwipeTouchListener.onSwipe = new Game.SwipeCards();
    }

    private void initializeObjects()
    {
        dataHelper = new DataHelper(intArrayCards , imageViewsNumbers);
        moving = new Moving(imageViewsNumbers);
        swipe = new Swipe(intArrayCards , textViewScore , dataHelper , moving);
        cardsController = new CardsController(imageViewsNumbers , intArrayCards);
    }

    private class SwipeCards implements OnSwipeTouchListener.onSwipeListener
    {
        @Override
        public void swipeRight() {
            setTempValues();
            tempScore = swipe.getScore();
            checkAllButtons("right");
            cardsController.insertCardNumberRandomly();
        }

        @Override
        public void swipeTop() {
            setTempValues();
            tempScore = swipe.getScore();
            checkAllButtons("up");
            cardsController.insertCardNumberRandomly();
        }

        @Override
        public void swipeBottom() {
            setTempValues();
            tempScore = swipe.getScore();
            checkAllButtons("down");
            cardsController.insertCardNumberRandomly();
        }

        @Override
        public void swipeLeft() {
            setTempValues();
            tempScore = swipe.getScore();
            checkAllButtons("left");
            cardsController.insertCardNumberRandomly();
        }
    }

    private void checkAllButtons(String direction)
    {
        if(isDirectionUpOrLeft(direction))
            checkButtonsForUpAndLeftDirection(direction);
        else
            checkButtonForDownAndRightDirection(direction);
    }

    private boolean isDirectionUpOrLeft(String direction)
    {
        return direction.equals("up") || (direction.equals("left"));
    }

    private void checkButtonsForUpAndLeftDirection(String direction)
    {
        for(int i = 0; i < imageViewsNumbers.length; ++i)
            for(int j = 0; j < imageViewsNumbers[i].length; ++j)
                setRowColumnValuesCheckButton(i , j , direction);
    }

    private void checkButtonForDownAndRightDirection(String direction)
    {
        for(int i = imageViewsNumbers.length - 1; i > -1; --i)
            for(int j = imageViewsNumbers.length - 1; j > -1; --j)
                setRowColumnValuesCheckButton(i , j , direction);
    }

    private void setRowColumnValuesCheckButton(int i , int j , String direction)
    {
        this.row = i;
        this.column = j;
        checkButton(direction);
    }


    private void checkButton(String direction)
    {
        setRowAndColumnValues();

        if(direction.equals("down"))
            swipe.checkButtonForDownSweep();

        else if(direction.equals("right"))
            swipe.checkButtonForRightSweep();

        else if(direction.equals("left"))
            swipe.checkButtonForLeftSweep();

        else if(direction.equals("up"))
            swipe.checkButtonForUpSweep();

        dataHelper.setImages();

    }


    private void setRowAndColumnValues()
    {
        swipe.setRow(row);
        swipe.setColumn(column);
    }

    private int[][] tempIntArray;
    private int tempScore;







    public void restartGame()
    {
        setIntArrayCardsElementsToZero();
        referenceAndSetImageButtons();
        cardsController.insertCardNumberRandomly();

        swipe.restartScore();
    }

    public void stepBackGame()
    {
        print(tempIntArray);
        print(intArrayCards);

        copyFromFirstToSecond(tempIntArray , intArrayCards);
        dataHelper.setImages();
        swipe.setScore(tempScore);
    }

    private void print(int [][]array)
    {
        for (int[] ints : array) {
            for (int j = 0; j < array.length; ++j)
                System.out.print(ints[j] + " ");

            System.out.println();
        }

        System.out.println();
    }

    private void setTempValues()
    {
        copyFromFirstToSecond(intArrayCards , tempIntArray);

        dataHelper.setImages();
    }

    private static void copyFromFirstToSecond(int first[][], int second[][])
    {
        if((first != null) && (second != null) && (first.length == second.length))
        {
            for(int i = 0; i < second.length; ++i)
                for(int j = 0; j < second.length; ++j)
                    second[i][j] = first[i][j];
        }
    }


}
