import java.lang.*;

/** project0, NBody is a class that will actually run your simulation.
    This class will have NO constructor. The goal of this class is to
    simulate a universe specified in one of the data files*/
public class NBody{

    /** Given a file name, it should return a double corresponding to
        the radius of the universe in that file */
    public static double readRadius(String file){
        In in = new In(file);

        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Given a file name, it should return an array of Planets corresponding
        to the planets in the file */
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int numPlanets = in.readInt();
        Planet[] planets = new Planet[numPlanets];
        double xPos = 0;
        double yPos = 0;
        double xVel = 0;
        double yVel = 0;
        double mass = 0;
        String img = "";
        in.readDouble();
        for(int i = 0; i < numPlanets; i++){
            xPos = in.readDouble();
            yPos = in.readDouble();
            xVel = in.readDouble();
            yVel = in.readDouble();
            mass = in.readDouble();
            img = in.readString();
            Planet p = new Planet(xPos, yPos, xVel, yVel, mass, img);
            planets[i] = p;
        }
        return planets;
    }

    /** build the functionality to draw the universe in its starting position */
    public static void main(String []args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        int length = planets.length;
        double radius = readRadius(filename);
        String universeImg = "images/starfield.jpg";
        double time = 0;

        StdDraw.setScale(-2 * radius, 2 * radius);
        StdDraw.enableDoubleBuffering();

        StdDraw.clear();
        StdDraw.picture(0, 0, universeImg);

        for(Planet p : planets){
            p.draw();
        }
        StdDraw.show();

        while(time <= T){
            double[] xForces = new double[length];
            double[] yForces = new double[length];
            for(int i = 0; i < length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, universeImg);
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}
    }
}
