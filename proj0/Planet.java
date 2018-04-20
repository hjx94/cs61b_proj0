
import java.lang.*;

public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
					double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		Planet temp = p;
		double dist = this.calcDistance(temp);
		double g = 6.67 * Math.pow(10, -11);
		double force = g * p.mass * this.mass / Math.pow (dist, 2);
		return force;
	}

	public Double calcForceExertedByX(Planet p){
		Planet temp = p;
		double f = this.calcForceExertedBy(temp);
		double dx = p.xxPos - this.xxPos;
		double dist = this.calcDistance(temp);
		double forceX = f * dx / dist;
		return forceX;
	}

	public Double calcForceExertedByY(Planet p){
		Planet temp = p;
		double f = this.calcForceExertedBy(temp);
		double dy = p.yyPos - this.yyPos;
		double dist = this.calcDistance(temp);
		double forceY = f * dy / dist;
		return forceY;
	}

	public Double calcNetForceExertedByX(Planet[] args){
		Double forceX = 0.0;
		for (int i=0; i<args.length;i++){
			Planet p = args[i];
			if (this.equals(p)){
				continue;
			}
			forceX += calcForceExertedByX(p);
		}
		return forceX;
	}

	public Double calcNetForceExertedByY(Planet[] args){
		Double forceY = 0.0;
		for (int i=0; i<args.length;i++){
			Planet p = args[i];
			if (this.equals(p)){
				continue;
			}
			forceY += calcForceExertedByY(p);
		}
		return forceY;
	}

	public void update(Double dt, Double fX, Double fY){
		Double aX = fX / this.mass;
		Double aY = fY / this.mass;
		Double dT = dt;
		this.xxVel = this.xxVel + dT * aX;
		this.yyVel = this.yyVel + dT * aY;
		this.xxPos = this.xxPos + dT * this.xxVel;
		this.yyPos = this.yyPos + dT * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}


}