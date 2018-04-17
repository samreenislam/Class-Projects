import java.awt.Rectangle;

//Samreen F. Islam (sfi6zy)
// Used code learned in class and Java API

public class Particle {
   private double x;
   private double y;
   private double vx;
   private double vy;
   private double mass;
   public Particle(double x, double y){
	   this.x = x;
	   this.y = y;
	   this.vx = 0;
	   this.vy = 0;
	   this.mass = 1;
   }
   public Particle(double x, double y, double vx, double vy){
	   this.x = x;
	   this.y = y;
	   this.vx = vx;
	   this.vy = vy;
	   this.mass = 1;
   }
   public Particle(double x, double y, double vx, double vy, double mass){
	   this.x = x;
	   this.y = y;
	   this.vx = vx;
	   this.vy = vy;
	   this.mass = mass;
   }
   public double getX(){
	   return this.x;
   }
   public int getIntX(){
	   return (int) this.x;
   }
   public void setX(double x){
	   this.x = x;
   }
   public double getY(){
	   return this.y;
   }
   public int getIntY(){
	   return (int) this.y;
   }
   public void setY(double y){
	   this.y = y;
   }
   public void applyForce(double fx, double fy, double dt){
	   double dvx = (fx*dt)/mass;
	   vx += dvx;
	   double dvy = (fy*dt)/mass;
	   vy += dvy;
   }
   public void timePasses(double dt) {
	   double dx = vx*dt;
	   x += dx;
	   double dy = vy*dt;
	   y += dy;
   }
   public double getSpeed(){
	   double speedsquare = vx*vx + vy*vy;
	   double speed = Math.sqrt(speedsquare);
	   return speed;
   }
   public void applyDrag(double drag, double dt){
	   double fx = -vx*getSpeed()*drag;
	   double fy = -vy*getSpeed()*drag;
	   applyForce(fx, fy, dt);
   }
   public void bounceIfOutsideOf(Rectangle r, double bounciness){
	   double left = r.getX();
	   double right = r.getX() + r.getWidth();
	   double top = r.getY();
	   double bottom = r.getY() + r.getHeight();
	   if(x < left){
		   x = left;
		   if (vx > 0) {
		   vx = vx*bounciness; 
		   } else { vx = -vx*bounciness; }
	   } else if(x > right){
		   x = right;
		   if (vx > 0) {
			   vx = -vx*bounciness; 
			   } else { vx = vx*bounciness; }
	   } 
	   if (y < top){
		   y = top;
		   if (vy > 0) {
			   vy = vy*bounciness; 
			   } else { vy = -vy*bounciness; }
	   } else if (y > bottom){
		   y = bottom;
		   if (vy > 0) {
			   vy = -vy*bounciness; 
			   } else { vy = vy*bounciness; }
	   } 
   }
   public String toString(){
	   return "Particle at <" + x + "," + y + "> with velocity <" + vx + "," + vy + "> and mass " + mass;
   }
   public static void main(String[] args){
	   Particle p1 = new Particle(10.0, 20.0);
	   System.out.println(p1.toString());                      // should return "Particle at <10.0,20.0> with velocity <0.0,0.0> and mass 1.0"
	   Particle p2 = new Particle(10.0, 20.0, 2.0, 2.0);
	   System.out.println(p2.toString());                      // should return "Particle at <10.0,20.0> with velocity <2.0,2.0> and mass 1.0"
	   Particle p3 = new Particle(10.0, 20.0, 1.0, 3.0, 4.0);
	   System.out.println(p3.toString());                      // should return "Particle at <10.0,20.0> with velocity <1.0,3.0> and mass 4.0"
	   p2.timePasses(0.02);
	   System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <2.0,2.0> and mass 1.0"
	   p2.applyForce(0, 9.8, 0.02);
	   System.out.println(p2.getSpeed());                      // should return 2.9702552078903928
	   System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <2.0,2.196> and mass 1.0"
	   p2.applyDrag(0.1, 0.02);
	   System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <1.9881189791684384,2.1829546391269457> and mass 1.0"
	   p2.bounceIfOutsideOf(new Rectangle(0,0, 10, 20), 0.5);
	   System.out.println(p2.toString());                      // should return "Particle at <10.0,20.0> with velocity <-0.9940594895842192,-1.0914773195634728> and mass 1.0"
   }
}
