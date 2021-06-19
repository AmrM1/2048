package com.example.a2048;

import android.widget.ImageView;

import java.util.Random;

public class CardsController
{
    private int tempRow , tempColumn;
    private Random random;

    private ImageView[][] imageViewsNumbers;
    private int intArrayCards[][];

    private int row, column;

    public CardsController(ImageView[][] imageViewsNumbers, int[][] intArrayCards)
    {
        this.imageViewsNumbers = imageViewsNumbers;
        this.intArrayCards = intArrayCards;
    }

    public void insertCardNumberRandomly()
    {
        initializeObjectRandom();

        if(isThereEmptyImageView())
        {
            do
                chooseTempRowAndColumnRandomly();
            while(isNumberOnThisPositionInArrayZero());

            setNumberTwoOnImageViewRandomly();
            setRandomlyGeneratedNumber2InIntArrayCards();

            setThoseValuesToThisRowAndColumn();
        }
    }

    private void initializeObjectRandom()
    {
        random = new Random();
    }

    private boolean isThereEmptyImageView()
    {
        for (int[] intArrayCard : intArrayCards)
            for (int j = 0; j < intArrayCards.length; ++j)
                if (intArrayCard[j] == 0)
                    return true;

        return false;
    }

    private void chooseTempRowAndColumnRandomly()
    {
        tempRow = random.nextInt(4);
        tempColumn = random.nextInt(4);
    }

    private boolean isNumberOnThisPositionInArrayZero() {
        return ((intArrayCards[tempRow][tempColumn]) != 0);
    }

    private void setNumberTwoOnImageViewRandomly()
    {
        imageViewsNumbers[tempRow][tempColumn].setImageResource(R.drawable.number2);
    }

    private void setRandomlyGeneratedNumber2InIntArrayCards() {
        intArrayCards[tempRow][tempColumn] = 2;
    }

    private void setThoseValuesToThisRowAndColumn()
    {
        this.row = tempRow;
        this.column = tempColumn;
    }

}
