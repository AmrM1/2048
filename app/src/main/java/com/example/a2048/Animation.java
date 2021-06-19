package com.example.a2048;

import android.widget.ImageView;

public class Animation
{

    private ImageView[][] imageViewsNumbers;

    private int rowTwo , columnTwo;

    private int translation , duration;

    public Animation(ImageView[][] imageViewsNumbers, int translation, int duration)
    {
        this.imageViewsNumbers = imageViewsNumbers;
        this.translation = translation;
        this.duration = duration;
    }

    public void runAnimation(int rowOne , int columnOne , String direction , int image)
    {
        removeSecondImageViewImage();

        translateNumberToSecondImageView(rowOne , columnOne , direction);

        setNewImageOnSecondImageView(image);
        setBlankImageOnFirstImageView(rowOne , columnOne);

        translateFirstImageViewOnDefaultPosition(rowOne , columnOne , direction);
    }

    private void removeSecondImageViewImage() {
        imageViewsNumbers[rowTwo][columnTwo].setImageResource(0);
    }

    private void translateNumberToSecondImageView(int rowOne , int columnOne , String direction)
    {
        if(isDirectionUpOrDown(direction))
            translateFirstImageOnYCoordinate(rowOne , columnOne);

        else
            translateFirstImageOnXCoordinate(rowOne , columnOne);
    }

    private boolean isDirectionUpOrDown(String direction) {
        return  ((direction.equals("up")) || (direction.equals("down")));
    }


    private void translateFirstImageOnYCoordinate(int rowOne , int columnOne) {
        imageViewsNumbers[rowOne][columnOne].animate().translationYBy((Math.abs(rowOne - rowTwo) * 210)).setDuration(duration);
    }

    private void translateFirstImageOnXCoordinate(int rowOne ,int columnOne)
    {
        imageViewsNumbers[rowOne][columnOne].animate().translationXBy(translation).setDuration(duration);
    }

    private void setNewImageOnSecondImageView(int image)
    {
        imageViewsNumbers[rowTwo][columnTwo].setImageResource(image);
    }

    private void setBlankImageOnFirstImageView(int rowOne , int columnOne){
        imageViewsNumbers[rowOne][columnOne].setImageResource(R.drawable.blank);
    }

    private void translateFirstImageViewOnDefaultPosition(int rowOne , int columnOne , String direction)
    {
        if(isDirectionUpOrDown(direction))
            setYCoordinateAtBeginning(rowOne , columnOne);
        else
            setXCoordinateAtBeginning(rowOne , columnOne);
    }


    private void setYCoordinateAtBeginning(int rowOne , int columnOne) {
        imageViewsNumbers[rowOne][columnOne].animate().translationYBy(0).setDuration(1);
    }

    private void setXCoordinateAtBeginning(int rowOne , int columnOne) {
        imageViewsNumbers[rowOne][columnOne].animate().translationXBy(0).setDuration(1);
    }


}
