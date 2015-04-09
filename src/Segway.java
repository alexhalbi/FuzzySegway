import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.GyroSensor;
import lejos.robotics.Gyroscope;
import nxtfuzzylogic.*;


public class Segway {

    private NXTFuzzyLogic nfl = new NXTFuzzyLogic();
    private static Gyroscope gyro = new GyroSensor(SensorPort.S1,604);
    
    
    public Segway() {
    	    	
        nfl = new NXTFuzzyLogic();

        nfl.defineInputLinguisticVariable("vel", -300, 300, 0);
        nfl.defineInputLinguisticVariable("angle", -90, 90, 0);

        nfl.defineTermZType("backLarge", "vel", -300, -200);
        nfl.defineTermTriangular("backMedium", "vel", -300, -200, -100);
        nfl.defineTermTriangular("backSmall", "vel", -200, -100, 0);
        nfl.defineTermTriangular("zero", "vel", -100, 0, 100);
        nfl.defineTermTriangular("fowSmall", "vel", 0, 100, 200);
        nfl.defineTermTriangular("fowMedium", "vel", 100, 200, 300);
        nfl.defineTermSType("fowLarge", "vel", 20, 300);

        nfl.defineTermZType("negLarge", "angle", -90, -60);
        nfl.defineTermTriangular("negMedium", "angle", -90, -60, -30);
        nfl.defineTermTriangular("negSmall", "angle", -60, -30, 0);
        nfl.defineTermTriangular("zeroAng", "angle", -30, 0, 30);
        nfl.defineTermTriangular("posSmall", "angle", 0, 30, 60);
        nfl.defineTermTriangular("posMedium","angle", 30, 60, 90);
        nfl.defineTermSType("posLarge", "angle", 60, 90);

        nfl.defineOutputLinguisticVariable("speed", -900, 900, 0, NXTFuzzyLogic.COG);

        nfl.defineTermZType("backVeryFast", "speed", -900, -420);
        nfl.defineTermTriangular("backFast", "speed", -530, -310, -190);
        nfl.defineTermTriangular("backMedium", "speed", -220, -160, -115);
        nfl.defineTermTriangular("backSlow", "speed", -155, -105, -55);
        nfl.defineTermTriangular("backVerySlow", "speed", -80, -40, 0);
        nfl.defineTermTriangular("stop", "speed", -15, 0, 15);
        nfl.defineTermTriangular("fowVerySlow", "speed", 0, 40, 80);
        nfl.defineTermTriangular("fowSlow", "speed", 55, 105, 155);
        nfl.defineTermTriangular("fowMedium", "speed", 115, 160, 220);
        nfl.defineTermTriangular("fowFast", "speed", 190, 310, 530);
        nfl.defineTermSType("fowVeryFast", "speed", 420, 900);

        //negLarge========================================================
        String[] r0 = {"negLarge", "backLarge"};
        nfl.defineRule(r0, "backVeryFast", NXTFuzzyLogic.MIN);
        String[] r1 = {"negLarge", "backMedium"};
        nfl.defineRule(r1, "backVeryFast", NXTFuzzyLogic.MIN);
        String[] r2 = {"negLarge", "backSmall"};
        nfl.defineRule(r2, "backVeryFast", NXTFuzzyLogic.MIN);
        String[] r3 = {"negLarge", "zero"};
        nfl.defineRule(r3, "backFast", NXTFuzzyLogic.MIN);
        String[] r4 = {"negLarge", "fowSmall"};
        nfl.defineRule(r4, "backFast", NXTFuzzyLogic.MIN);
        String[] r5 = {"negLarge", "fowMedium"};
        nfl.defineRule(r5, "backMedium", NXTFuzzyLogic.MIN);
        String[] r6 = {"negLarge", "fowLarge"};
        nfl.defineRule(r6, "backMedium", NXTFuzzyLogic.MIN);

        //negMedium ======================================================
        String[] r7 = {"negMedium", "backLarge"};
        nfl.defineRule(r7, "backVeryFast", NXTFuzzyLogic.MIN);
        String[] r8 = {"negMedium", "backMedium"};
        nfl.defineRule(r8, "backFast", NXTFuzzyLogic.MIN);
        String[] r9 = {"negMedium", "backSmall"};
        nfl.defineRule(r9, "backFast", NXTFuzzyLogic.MIN);
        String[] r10 = {"negMedium", "zero"};
        nfl.defineRule(r10, "backMedium", NXTFuzzyLogic.MIN);
        String[] r11 = {"negMedium", "fowSmall"};
        nfl.defineRule(r11, "backMedium", NXTFuzzyLogic.MIN);
        String[] r12 = {"negMedium", "fowMedium"};
        nfl.defineRule(r12, "backSlow", NXTFuzzyLogic.MIN);
        String[] r13 = {"negMedium", "fowLarge"};
        nfl.defineRule(r13, "backSlow", NXTFuzzyLogic.MIN);

        //negSmall========================================================
        String[] r14 = {"negSmall", "backLarge"};
        nfl.defineRule(r14, "backFast", NXTFuzzyLogic.MIN);
        String[] r15 = {"negSmall", "backMedium"};
        nfl.defineRule(r15, "backMedium", NXTFuzzyLogic.MIN);
        String[] r16 = {"negSmall", "backSmall"};
        nfl.defineRule(r16, "backMedium", NXTFuzzyLogic.MIN);
        String[] r17 = {"negSmall", "zero"};
        nfl.defineRule(r17, "backSlow", NXTFuzzyLogic.MIN);
        String[] r18 = {"negSmall", "fowSmall"};
        nfl.defineRule(r18, "backSlow", NXTFuzzyLogic.MIN);
        String[] r19 = {"negSmall", "fowMedium"};
        nfl.defineRule(r19, "backVerySlow", NXTFuzzyLogic.MIN);
        String[] r20 = {"negSmall", "fowLarge"};
        nfl.defineRule(r20, "backVerySlow", NXTFuzzyLogic.MIN);


        //zeroAng=========================================================
        String[] r21 = {"zeroAng", "backLarge"};
        nfl.defineRule(r21, "backSlow", NXTFuzzyLogic.MIN);
        String[] r22 = {"zeroAng", "backMedium"};
        nfl.defineRule(r22, "backVerySlow", NXTFuzzyLogic.MIN);
        String[] r23 = {"zeroAng", "backSmall"};
        nfl.defineRule(r23, "backVerySlow", NXTFuzzyLogic.MIN);
        String[] r24 = {"zeroAng", "zero"};
        nfl.defineRule(r24, "stop", NXTFuzzyLogic.MIN);
        String[] r25 = {"zeroAng", "fowSmall"};
        nfl.defineRule(r25, "fowVerySlow", NXTFuzzyLogic.MIN);
        String[] r26 = {"zeroAng", "fowMedium"};
        nfl.defineRule(r26, "fowVerySlow", NXTFuzzyLogic.MIN);
        String[] r27 = {"zeroAng", "fowLarge"};
        nfl.defineRule(r27, "fowSlow", NXTFuzzyLogic.MIN);


        //posSmall========================================================
        String[] r28 = {"posSmall", "backLarge"};
        nfl.defineRule(r28, "fowVerySlow", NXTFuzzyLogic.MIN);
        String[] r29 = {"posSmall", "backMedium"};
        nfl.defineRule(r29, "fowVerySlow", NXTFuzzyLogic.MIN);
        String[] r30 = {"posSmall", "backSmall"};
        nfl.defineRule(r30, "fowSlow", NXTFuzzyLogic.MIN);
        String[] r31 = {"posSmall", "zero"};
        nfl.defineRule(r31, "fowSlow", NXTFuzzyLogic.MIN);
        String[] r32 = {"posSmall", "fowSmall"};
        nfl.defineRule(r32, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r33 = {"posSmall", "fowMedium"};
        nfl.defineRule(r33, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r34 = {"posSmall", "fowLarge"};
        nfl.defineRule(r34, "fowFast", NXTFuzzyLogic.MIN);

        //posMedium=======================================================
        String[] r35 = {"posMedium", "backLarge"};
        nfl.defineRule(r35, "fowSlow", NXTFuzzyLogic.MIN);
        String[] r36 = {"posMedium", "backMedium"};
        nfl.defineRule(r36, "fowSlow", NXTFuzzyLogic.MIN);
        String[] r37 = {"posMedium", "backSmall"};
        nfl.defineRule(r37, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r38 = {"posMedium", "zero"};
        nfl.defineRule(r38, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r39 = {"posMedium", "fowSmall"};
        nfl.defineRule(r39, "fowFast", NXTFuzzyLogic.MIN);
        String[] r40 = {"posMedium", "fowMedium"};
        nfl.defineRule(r40, "fowFast", NXTFuzzyLogic.MIN);
        String[] r41 = {"posMedium", "fowLarge"};
        nfl.defineRule(r41, "fowVeryFast", NXTFuzzyLogic.MIN);

        //kat - posLarge========================================================
        String[] r42 = {"posLarge", "backLarge"};
        nfl.defineRule(r42, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r43 = {"posLarge", "backMedium"};
        nfl.defineRule(r43, "fowMedium", NXTFuzzyLogic.MIN);
        String[] r44 = {"posLarge", "backSmall"};
        nfl.defineRule(r44, "fowFast", NXTFuzzyLogic.MIN);
        String[] r45 = {"posLarge", "zero"};
        nfl.defineRule(r45, "fowFast", NXTFuzzyLogic.MIN);
        String[] r46 = {"posLarge", "fowSmall"};
        nfl.defineRule(r46, "fowVeryFast", NXTFuzzyLogic.MIN);
        String[] r47 = {"posLarge", "fowMedium"};
        nfl.defineRule(r47, "fowVeryFast", NXTFuzzyLogic.MIN);
        String[] r48 = {"posLarge", "fowLarge"};
        nfl.defineRule(r48, "fowVeryFast", NXTFuzzyLogic.MIN);

        nfl.init();
        
        
    }

    public void setInput(int vel, int angle){
        nfl.setInputValue("vel", vel);
        nfl.setInputValue("angle", angle);
    }

    public int getOutput(){
        nfl.evaluate();
        return (int) nfl.getOutputValue("speed");
    }
    
    public static void main(String[] args) {
    	Segway segway = new Segway();
      	int angle=calibrate();
      	LCD.clear();
      	float lastTime = System.currentTimeMillis();
      	int vel = segway.getVelocity();
      	segway.setInput(vel,angle);
      	
		while(!Button.ESCAPE.isDown()){
			
			float time = lastTime - System.currentTimeMillis();
			
			vel = segway.getVelocity();
			angle +=(int)(time*vel);
			LCD.drawString("Diff: "+(time*vel),1,4);
					
			if(angle > 90){
				angle = 90;
			}else if(angle < -90){
				angle = -90;
			}
			segway.setInput(vel,angle);
	      	int speed = segway.getOutput();
	      	LCD.drawString("Speed: "+speed, 1, 1);
	      	LCD.drawString("Vel: "+vel,1,2);
	     	LCD.drawString("Angle: "+angle,1,3);
	     
	     	
	      	Motor.A.setSpeed(speed);
	      	Motor.C.setSpeed(speed);
	      	if(speed > 0 ){
	      		Motor.A.backward();
		      	Motor.C.backward();
	      	} else {
	      		Motor.A.forward();
		      	Motor.C.forward();
	      	}
			
			lastTime = System.currentTimeMillis();
	      	
		}
      	
    }

	private static int calibrate() {
	
		LCD.clear();
		LCD.drawString("Segway bitte aufstellen und an der Stütze halten.", 0, 0);
		LCD.drawString("Dann orangen Knopf drücken und Stütze loslassen.",0,2);
		int startValue = 0;
		while(!Button.ENTER.isDown()){
		
		}
		return startValue;
	}
	public int getVelocity(){
		int vel=0;
		for(int i=0;i<2;i++){
			vel += (int) gyro.getAngularVelocity();
		}
		
		return vel/3;
	}
}