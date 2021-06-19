package com.example.a2048;

import android.widget.ImageView;

public class DataHelper
{

    private int intArrayCards[][];
    private ImageView[][] imageViewsNumbers;

    public DataHelper(int[][] intArrayCards, ImageView[][] imageViewsNumbers)
    {
        this.intArrayCards = intArrayCards;
        this.imageViewsNumbers = imageViewsNumbers;
    }

    public void setImages()
    {
        for(int i = 0; i < imageViewsNumbers.length; ++i)
            for(int j = 0; j < imageViewsNumbers[0].length; ++j)
            {
                if(intArrayCards[i][j] == 0)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.blank);

                else if(intArrayCards[i][j] == 2)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number2);

                else if(intArrayCards[i][j] == 4)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number4);

                else if(intArrayCards[i][j] == 8)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number8);

                else if(intArrayCards[i][j] == 16)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number16);

                else if(intArrayCards[i][j] == 32)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number32);

                else if(intArrayCards[i][j] == 64)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number64);

                else if(intArrayCards[i][j] == 128)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number128);

                else if(intArrayCards[i][j] == 256)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number256);

                else if(intArrayCards[i][j] == 512)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number512);

                else if(intArrayCards[i][j] == 1024)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number1024);

                else if(intArrayCards[i][j] == 2048)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number2048);

                else if(intArrayCards[i][j] == 4096)
                    imageViewsNumbers[i][j].setImageResource(R.drawable.number4096);
            }
    }

    public int getImage(int row , int column)
    {
        if((intArrayCards[row][column]) == 0)
            return R.drawable.blank;

        else if((intArrayCards[row][column]) == 2)
            return R.drawable.number2;

        else if((intArrayCards[row][column]) == 4)
            return R.drawable.number4;

        else if((intArrayCards[row][column]) == 8)
            return R.drawable.number8;

        else if((intArrayCards[row][column]) == 16)
            return R.drawable.number16;

        else if((intArrayCards[row][column]) == 32)
            return R.drawable.number32;

        else if((intArrayCards[row][column]) == 64)
            return R.drawable.number64;

        else if((intArrayCards[row][column]) == 128)
            return R.drawable.number128;

        else if((intArrayCards[row][column]) == 256)
            return R.drawable.number256;

        else if((intArrayCards[row][column]) == 512)
            return R.drawable.number512;

        else if((intArrayCards[row][column]) == 1024)
            return R.drawable.number1024;

        else if((intArrayCards[row][column]) == 2048)
            return R.drawable.number2048;

        else if((intArrayCards[row][column]) == 4096)
            return R.drawable.number4096;

        return R.drawable.blank;
    }

    public int getImageUsingCurrentNumber(int i)
    {
        if(i == 2)
            return R.drawable.number4;

        else if(i == 4)
            return R.drawable.number8;

        else if(i == 8)
            return R.drawable.number16;

        else if(i == 16)
            return R.drawable.number32;

        else if(i == 32)
            return R.drawable.number64;

        else if(i == 64)
            return R.drawable.number128;

        else if(i == 128)
            return R.drawable.number256;

        else if(i == 256)
            return R.drawable.number512;

        else if(i == 512)
            return R.drawable.number1024;

        else if(i == 1024)
            return R.drawable.number2048;

        else if(i == 2048)
            return R.drawable.number4096;

        return R.drawable.blank;
    }
}
