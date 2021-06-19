package com.example.a2048;

import android.widget.ImageView;

public class Moving
{
    private int rowOne , columnOne;
    private int rowTwo , columnTwo;
    private int translation , duration;

    private boolean correctSetDirectionValues;

    private Animation animation;

    private ImageView[][] imageViewsNumbers;

    public Moving(ImageView[][] imageViewsNumbers)
    {
        this.animation = new Animation(imageViewsNumbers , translation , duration);
        this.imageViewsNumbers = imageViewsNumbers;
    }

    public void move(int rowOne , int columnOne , int places , String direction , int image)
    {
        setTranslationAndDurationValues(direction , places);
        setValuesForAllDirections(places , direction);

        if(areValuesSetCorrectly())
            animation.runAnimation(rowOne , columnOne , direction , image);

    }

    private void setTranslationAndDurationValues(String direction , int places)
    {
        setTranslation(places * 210);
        setDuration(1);

        if (ifDirectionIsOnLeftOrDown(direction))
            setTranslationNegative();
    }

    private void setTranslation(int translation) {
        this.translation = translation;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private boolean ifDirectionIsOnLeftOrDown(String direction)
    {
        return  ((direction.equals("left")) || (direction.equals("down")));
    }

    private void setTranslationNegative() {
        translation *= -1;
    }



    private void setValuesForAllDirections(int places , String direction)
    {
        if(isDirectionRight(direction))
            setValuesForRightDirection(places);

        else if(isDirectionLeft(direction))
            setValuesForLeftDirection(places);

        else if(isDirectionUp(direction))
            setValuesForUpDirection(places);

        else if(isDirectionDown(direction))
            setValuesForDownDirection(places);
    }

    private boolean isDirectionRight(String description) {
        return description.equals("right");
    }

    private boolean isDirectionLeft(String description) {
        return description.equals("left");
    }

    private boolean isDirectionUp(String description) {
        return description.equals("up");
    }

    private boolean isDirectionDown(String description) {
        return description.equals("down");
    }



    private void setValuesForRightDirection(int places)
    {
        if((columnOne + places) < imageViewsNumbers.length)
        {
            columnTwo = columnOne + places;
            rowTwo = rowOne;
            correctSetDirectionValues = true;
        }
    }

    private void setValuesForLeftDirection( int places)
    {
        if((columnOne - places) >= 0)
        {
            columnTwo = columnOne - places;
            rowTwo = rowOne;
            correctSetDirectionValues = true;
        }
    }

    private void setValuesForUpDirection(int places)
    {
        if((rowOne - places) >= 0)
        {
            rowTwo = rowOne - places;
            columnTwo = columnOne;
            correctSetDirectionValues = true;
        }
    }

    private void setValuesForDownDirection(int places)
    {
        if(rowOne + places < imageViewsNumbers.length)
        {
            rowTwo = rowOne + places;
            columnTwo = columnOne;
            correctSetDirectionValues = true;
        }
    }

    private boolean areValuesSetCorrectly()
    {
        return (correctSetDirectionValues);
    }

}
