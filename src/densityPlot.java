import java.util.Scanner;
import java.io.File;

public class densityPlot{
    private final double AL=26.982;
    private final double SI=28.086;
    private final double H=1.0079;
    private final double O=15.999;
    private final double UNIT=1660;
    /**
     * A unit of mass used to express atomic and molecular weights
     * => equal to one-twelfth of the mass of an atom of carbon-12
     * => equal to approximately 1.660539040(20)×10−27 kg.
     * Angstrom
     * =>1 Ångström =10^-10 m
     * density unit =>1.66 x 10-27 kg/ Ångström^3 => 1.66 x 10^3 =1660 kg/m^3
     * */

    public void densityByZ(String pathname)throws Exception{
        Scanner in=new Scanner(new File(pathname+"\\total.xyz"));
        System.out.println(pathname+"\\total.xyz");
        int frame=0;
        int num=in.nextInt();
        double Ceil=20,Floor=10;
        int length=0;
        for(int i=0;i<num;i++){
            in.next();
            in.next();
            in.next();
            String temp=in.next();
            if(Ceil<Double.parseDouble(temp))Ceil=Double.parseDouble(temp);
            if(Floor>Double.parseDouble(temp))Floor=Double.parseDouble(temp);
            Ceil=Math.ceil(Ceil);
            Floor=Math.floor(Floor);
        }
        length=(int)(Ceil-Floor);
        double[] Zden=new double[length];
        int[] Zseg=new int[length];
        for(int i=0;i<length;i++){
            Zseg[i]=(int)(Floor+i);
            System.out.println(Zseg[i]);
            Zden[i]=0.;
            System.out.println(Zden[i]);
        }
        System.out.println(Ceil+" "+Floor);
        in.reset();
        while(in.hasNext()){
            in.next();
            frame++;
            for(int i=0;i<num;i++){
                String name=in.next();
                in.next();
                in.next();
                double z=in.nextDouble();
                for(int j=1;j<length;j++){
                    if(Zseg[j]>z){
                        Zden[j-1]+=densityCalc(name);
                        break;
                    }
                    if(j==length-1)Zden[j]+=densityCalc(name);
                }
            }
        }
        in.close();
        for(int i=0;i<length;i++){
            System.out.println((Zseg[i]+0.5)+"\t"+Zden[i]/frame/1000);
        }

    }

    private double densityCalc(String name){
        double Area=76.7827;
        if(name.equals("Al"))
            return AL/Area*UNIT;
        else if(name.equals("Si"))
            return SI/Area*UNIT;
        else if(name.equals("H"))
            return H/Area*UNIT;
        else if(name.equals("O"))
            return O/Area*UNIT;
        else return 0;
    }
}
