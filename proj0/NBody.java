

public class NBody{
	public static Double readRadius(String fileName){
		In in = new In(fileName);
		int number = in.readInt();
		Double radius = in.readDouble();
		return radius;
	}

	public static Planet [] readPlanets(String fileName){
		In in = new In(fileName);
		int number = in.readInt();
		Double radius = in.readDouble();
		Double xP, yP, xV, yV, m;
		String img;
		Planet[] planetParam = new Planet[number];

		for(int i=0; i<number; i++){
			xP = in.readDouble();
			yP = in.readDouble();
			xV = in.readDouble();
			yV = in.readDouble();
			m = in.readDouble();
			img = in.readString();
			planetParam[i] = new Planet(xP, yP, xV, yV, m, "images/"+img);
		}
		return planetParam;
	}

	public static void main(String[] args){
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String file = args[2];
		Double radiusUniverse = readRadius(file);
		Planet[] allPlanets = readPlanets(file);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radiusUniverse, radiusUniverse);
		Double time = 0.0;

		while (time < T){
			Double[] xForces = new Double[allPlanets.length];
			Double[] yForces = new Double[allPlanets.length];

			for(int i=0;i<allPlanets.length;i++){
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}

			for(int i=0;i<allPlanets.length;i++){
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}
			String imageToDraw = "images/starfield.jpg";
			StdDraw.picture(0, 0, imageToDraw, radiusUniverse*2, radiusUniverse*2);
			
			for(Planet planet : allPlanets){
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
			
		}		

		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radiusUniverse);
		for (int i = 0; i < allPlanets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}


	
}