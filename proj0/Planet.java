import java.lang.*;

/** project 0, the planet class */
public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private final static double G = 6.67e-11;

    /** first constructor */
    public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** second constructor */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** calculates the distance between two Planets */
    public double calcDistance(Planet p){
        double dx = 0;
        double dy = 0;
        double r = 0;

        dx = p.xxPos - this.xxPos;
        dy = p.yyPos - this.yyPos;
        r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        return r;
    }

    /** takes in a planet, and returns a double describing the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet p){
        double m1 = this.mass;
        double m2 = p.mass;
        double r = this.calcDistance(p);

        return (G * m1 * m2) / Math.pow(r,2);
    }

    /** return the force exerted in the X directions */
    public double calcForceExertedByX(Planet p){
        double F = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);

        return F * dx / r;
    }

    /** return the force exerted in the Y directions */
    public double calcForceExertedByY(Planet p){
        double F = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);

        return F * dy / r;
    }

    /**take in an array of Planets and calculate the net Xforce exerted by
       all planets in that array upon the current Planet */
    public double calcNetForceExertedByX(Planet parray[]){
        double sumX = 0;

        for(Planet p : parray){
            if(!this.equals(p)){
                sumX = sumX + this.calcForceExertedByX(p);
            }
        }
        return sumX;
    }

    /**take in an array of Planets and calculate the net Yforce exerted by
       all planets in that array upon the current Planet */
    public double calcNetForceExertedByY(Planet parray[]){
        double sumY = 0;

        for(Planet p : parray){
            if(!this.equals(p)){
                sumY = sumY + this.calcForceExertedByY(p);
            }
        }
        return sumY;
    }

    /** determines how much the forces exerted on the planet will cause
        that planet to accelerate, and the resulting change in the planet’s
        velocity and position in a small period of time dt */
    public void update(double dt, double fx, double fy){
        double ax = 0;
        double ay = 0;

        ax = fx / this.mass;
        ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    /** uses the StdDraw API mentioned above to draw the Planet’s image at
        the Planet’s position */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
    }
}
