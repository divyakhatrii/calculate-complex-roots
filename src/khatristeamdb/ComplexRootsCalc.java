 /* 3/25/20 Divya Khatri
This class calculates the nth roots and sends it back to the input class
 */
package khatristeamdb;

public class ComplexRootsCalc
{

    //declare variables
    private double radius;
    private double theta;
    private int power;
    private double radius2Power;
    private double[] angles;
    private Object[] cosSolutions;
    private Object[] sinSolutions;
    private boolean angleRadian;
    //declare constants
    private final double SQUARE_POWER = 2;
    private final int CIRCLE_DEGREES = 360;
    private final int TAN_PERIOD = 180;
    private final double HUNDREDTHS_CONSTANT = 100;
    private final double EVEN_CONSTANT = 2;

    public ComplexRootsCalc(double radius, double theta, int power, boolean angleRadian)//add boolean angleRadian
    {
        this.radius = radius;
        this.theta = theta;
        this.power = power;
        this.radius2Power = Math.pow(radius, (1 / (double) power));
        angles = new double[power];
        cosSolutions = new Object[power];
        sinSolutions = new Object[power];

        //round radius raised to the power to the nearest hundredth
        radius2Power = (Math.round(HUNDREDTHS_CONSTANT * radius2Power));
        radius2Power = radius2Power / HUNDREDTHS_CONSTANT;

        //convert theta to degrees for consistency
        if (angleRadian)
        {
            this.theta = Math.toDegrees(theta);
        }

        for (int i = 0; i < power; i++)
        {
            //converts radian angles into degrees for consistency
            if (angleRadian)
            {
                angles[i] = Math.toDegrees(angles[i]);
            }
            //uses Demoivre's theorem to find angles
            angles[i] = (theta / (double) power) + ((CIRCLE_DEGREES * i) / (double) power);

            //checks for unit circle cases, so that these won't be in decimal, but fractional form
            if (angles[i] == 30)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√3";
                    sinSolutions[i] = (radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√3/2";
                    sinSolutions[i] = radius2Power + "i/2";
                }
            }

            else if (angles[i] == 45)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√2";
                    sinSolutions[i] = (radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√2/2";
                    sinSolutions[i] = radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 60)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2);
                    sinSolutions[i] = (radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "/2";
                    sinSolutions[i] = radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 90)
            {
                cosSolutions[i] = 0;
                sinSolutions[i] = radius2Power;
            }

            else if (angles[i] == 120)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2);
                    sinSolutions[i] = (radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "/2";
                    sinSolutions[i] = radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 135)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√2";
                    sinSolutions[i] = (radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√2/2";
                    sinSolutions[i] = radius2Power + "√2i/2";
                }
            }
            else if (angles[i] == 150)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√3";
                    sinSolutions[i] = (radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√3/2";
                    sinSolutions[i] = radius2Power + "i/2";
                }
            }

            else if (angles[i] == 180)
            {
                //checks if divisible by 2
                cosSolutions[i] = -radius2Power;
                sinSolutions[i] = 0;
            }

            else if (angles[i] == 210)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√3";
                    sinSolutions[i] = (-radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√3/2";
                    sinSolutions[i] = -radius2Power + "i/2";
                }
            }

            else if (angles[i] == 225)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√2";
                    sinSolutions[i] = (-radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√2/2";
                    sinSolutions[i] = -radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 240)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2);
                    sinSolutions[i] = (-radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "/2";
                    sinSolutions[i] = -radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 270)
            {
                cosSolutions[i] = 0;
                sinSolutions[i] = -radius2Power + "i";
            }

            else if (angles[i] == 300)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2);
                    sinSolutions[i] = (-radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "/2";
                    sinSolutions[i] = -radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 315)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√2";
                    sinSolutions[i] = (-radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√2/2";
                    sinSolutions[i] = -radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 330)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√3";
                    sinSolutions[i] = (-radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√3/2";
                    sinSolutions[i] = -radius2Power + "i/2";
                }
            }

            //when its not a unit circle case
            else
            {
                cosSolutions[i] = radius2Power * Math.cos(Math.toRadians(angles[i]));
                cosSolutions[i] = ((Math.round(HUNDREDTHS_CONSTANT * (double) cosSolutions[i])) / HUNDREDTHS_CONSTANT);
                sinSolutions[i] = radius2Power * Math.sin(Math.toRadians(angles[i]));
                sinSolutions[i] = ((Math.round(HUNDREDTHS_CONSTANT * (double) sinSolutions[i])) / HUNDREDTHS_CONSTANT) + "i";
            }

            //convert the angles back to their original forms for output purposes
            if (angleRadian)
            {
                angles[i] = Math.toRadians(angles[i]);
            }
            angles[i] = Math.round(HUNDREDTHS_CONSTANT * angles[i]) / HUNDREDTHS_CONSTANT;
        }
    }

    public ComplexRootsCalc()
    {
        this.radius2Power = 0;
        this.theta = 0;
        this.power = 0;
    }

    public void setRadius(double radius)
    {
        this.radius2Power = radius;
    }

    public double getRadius()
    {
        return this.radius2Power;
    }

    public void setTheta(double theta)
    {
        this.theta = theta;
    }

    public double getTheta()
    {
        return this.theta;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public int getPower()
    {
        return this.power;
    }

    public void setRadiusToPower(double radiusToPower)
    {
        this.radius2Power = radiusToPower;
    }

    public double getRadiusToPower()
    {
        return this.radius2Power;
    }

    public void setAngles(double[] angles)
    {
        this.angles = angles;
    }

    public double[] getAngles()
    {
        return this.angles;
    }

    public void setCosSolutions(Object[] cosSolutions)
    {
        this.cosSolutions = cosSolutions;
    }

    public Object[] getCosSolutions()
    {
        return this.cosSolutions;
    }

    public void setSinSolutions(Object[] sinSolutions)
    {
        this.sinSolutions = sinSolutions;
    }

    public Object[] getSinSolutions()
    {
        return this.sinSolutions;
    }

    public void setAngleRadians(boolean angleRadian)
    {
        this.angleRadian = angleRadian;
    }

    public boolean getAngleRadians()
    {
        return this.angleRadian;
    }

    public String toString()
    {
        return "The radius, " + radius + ", to the power 1/" + power + " is " + radius2Power;
    }

    //make last two set and get
    //to stirng
    //one more constructor
    public void RectangularComplexRoots(double a, double b, int power, boolean angleRadian)
    {
        //converts it into polar form
        this.power = power;
        this.angles = new double[power];
        this.cosSolutions = new Object[power];
        this.sinSolutions = new Object[power];
        this.radius = Math.pow((Math.pow(a, SQUARE_POWER) + Math.pow(b, SQUARE_POWER)), (1 / SQUARE_POWER));

        //round the radius
        this.radius = (Math.round(HUNDREDTHS_CONSTANT * radius)) / HUNDREDTHS_CONSTANT;
        this.radius2Power = Math.pow(radius, 1 / (double) power);

        //round radius raised to the power to the nearest hundredth
        radius2Power = (Math.round(HUNDREDTHS_CONSTANT * radius2Power));
        radius2Power = radius2Power / HUNDREDTHS_CONSTANT;

        //find theta and convert to degrees for readability
        this.theta = Math.atan(b / a);
        this.theta = Math.toDegrees(this.theta);

        //due to the period of tan inverse, we must adjust negative angles to the period of sin and cosine
        if (a < 0)
        {
            this.theta = this.theta + (TAN_PERIOD);
        }
        else if (b < 0)
        {
            this.theta = this.theta + (2 * TAN_PERIOD);
        }

        for (int i = 0; i < power; i++)
        {
            //converts radian angles into degrees for consistency
            if (angleRadian)
            {
                angles[i] = Math.toDegrees(angles[i]);
            }
            //uses Demoivre's theorem to find angles
            angles[i] = (theta / (double) power) + ((CIRCLE_DEGREES * i) / (double) power);

            //checks for unit circle cases, so that these won't be in decimal, but fractional form
            if (angles[i] == 30)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√3";
                    sinSolutions[i] = (radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√3/2";
                    sinSolutions[i] = radius2Power + "i/2";
                }
            }

            else if (angles[i] == 45)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√2";
                    sinSolutions[i] = (radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√2/2";
                    sinSolutions[i] = radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 60)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2);
                    sinSolutions[i] = (radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "/2";
                    sinSolutions[i] = radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 90)
            {
                cosSolutions[i] = 0;
                sinSolutions[i] = radius2Power;
            }

            else if (angles[i] == 120)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2);
                    sinSolutions[i] = (radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "/2";
                    sinSolutions[i] = radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 135)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√2";
                    sinSolutions[i] = (radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√2/2";
                    sinSolutions[i] = radius2Power + "√2i/2";
                }
            }
            else if (angles[i] == 150)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√3";
                    sinSolutions[i] = (radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√3/2";
                    sinSolutions[i] = radius2Power + "i/2";
                }
            }

            else if (angles[i] == 180)
            {
                //checks if divisible by 2
                cosSolutions[i] = -radius2Power;
                sinSolutions[i] = 0;
            }

            else if (angles[i] == 210)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√3";
                    sinSolutions[i] = (-radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√3/2";
                    sinSolutions[i] = -radius2Power + "i/2";
                }
            }

            else if (angles[i] == 225)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2) + "√2";
                    sinSolutions[i] = (-radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "√2/2";
                    sinSolutions[i] = -radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 240)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (-radius2Power / 2);
                    sinSolutions[i] = (-radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = -radius2Power + "/2";
                    sinSolutions[i] = -radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 270)
            {
                cosSolutions[i] = 0;
                sinSolutions[i] = -radius2Power + "i";
            }

            else if (angles[i] == 300)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2);
                    sinSolutions[i] = (-radius2Power / 2) + "√3i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "/2";
                    sinSolutions[i] = -radius2Power + "√3i/2";
                }
            }

            else if (angles[i] == 315)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√2";
                    sinSolutions[i] = (-radius2Power / 2) + "√2i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√2/2";
                    sinSolutions[i] = -radius2Power + "√2i/2";
                }
            }

            else if (angles[i] == 330)
            {
                //checks if divisible by 2
                if ((radius2Power / EVEN_CONSTANT) - (int) (radius2Power / EVEN_CONSTANT) == 0)
                {
                    cosSolutions[i] = (radius2Power / 2) + "√3";
                    sinSolutions[i] = (-radius2Power / 2) + "i";
                }
                else
                {
                    cosSolutions[i] = radius2Power + "√3/2";
                    sinSolutions[i] = -radius2Power + "i/2";
                }
            }

            //when its not a unit circle case
            else
            {
                cosSolutions[i] = radius2Power * Math.cos(Math.toRadians(angles[i]));
                cosSolutions[i] = ((Math.round(HUNDREDTHS_CONSTANT * (double) cosSolutions[i])) / HUNDREDTHS_CONSTANT);
                sinSolutions[i] = radius2Power * Math.sin(Math.toRadians(angles[i]));
                sinSolutions[i] = ((Math.round(HUNDREDTHS_CONSTANT * (double) sinSolutions[i])) / HUNDREDTHS_CONSTANT) + "i";
            }

            //convert the angles back to their original forms for output purposes
            if (angleRadian)
            {
                angles[i] = Math.toRadians(angles[i]);
            }
            angles[i] = Math.round(HUNDREDTHS_CONSTANT * angles[i]) / HUNDREDTHS_CONSTANT;
        }
    }

    public static void main(String[] args)
    {
//        ComplexRootsCalc rootCalcObj = new ComplexRootsCalc(1000, 0, 3, false);
//        System.out.println(rootCalcObj);
        ComplexRootsCalc rectObj = new ComplexRootsCalc();
        rectObj.RectangularComplexRoots(1000, 0, 3, false);
        System.out.println(rectObj);
    }

}
