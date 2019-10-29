import java.lang.*;

/** project 0, the body class */
public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** first constructor */
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** second constructor */
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = 0;
        double dy = 0;
        double r = 0;

        dx = b.xxPos - this.xxPos;
        dy = b.yyPos - this.yyPos;
        r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        return r;
    }
}
