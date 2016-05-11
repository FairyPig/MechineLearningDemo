package BP;

public class TrainData {
	double[][] traindata;
	double[][] traindataoutput;
	double[][] testdata;
	double[][] testdataoutput;
	public TrainData(){
		traindata = new double[][]{
			new double[]{1,1,1,1,1,1,0.697,0.460},	
			new double[]{0.5,1,0.5,1,1,1,0.774,0.376},
			new double[]{0.5,1,1,1,1,1,0.634,0.264},
			//new double[]{1,1,0.5,1,1,1,0.608,0.318,1},
			//new double[]{0,1,1,1,1,1,0.556,0.215,1},
			new double[]{1,0.5,1,1,0.5,0,0.403,0.237},
			new double[]{0.5,0.5,1,0.5,0.5,0,0.481,0.149},
			//new double[]{0.5,0.5,1,1,0.5,1,0.437,0.211,1},
			
			//new double[]{0.5,0.5,0.5,0.5,0.5,1,0.666,0.091,0},
			//new double[]{1,0,0,1,0,0,0.243,0.267,0},
			//new double[]{0,0,0,0,0,1,0.245,0.057,0},
			//new double[]{0,1,1,0,0,0,0.343,0.099,0},
			new double[]{1,0.5,1,0.5,1,1,0.639,0.161},
			new double[]{0,0.5,0,0.5,1,1,0.657,0.198},
			new double[]{0.5,0.5,1,1,0.5,0,0.360,0.370},
			new double[]{0,1,1,0,0,1,0.593,0.042},
			new double[]{1,1,0.5,0.5,0.5,1,0.719,0.103}
		};
		traindataoutput = new double[][]{
			new double[]{1},
			new double[]{1},
			new double[]{1},
			new double[]{1},
			new double[]{1},
			new double[]{0},
			new double[]{0},
			new double[]{0},
			new double[]{0},
			new double[]{0},
		};
		testdata = new double[][]{
			new double[]{1,1,0.5,1,1,1,0.608,0.318},
			new double[]{0,1,1,1,1,1,0.556,0.215},
			new double[]{0.5,0.5,1,1,0.5,1,0.437,0.211},
			
			new double[]{0.5,0.5,0.5,0.5,0.5,1,0.666,0.091},
			new double[]{1,0,0,1,0,0,0.243,0.267},
			new double[]{0,0,0,0,0,1,0.245,0.057},
			new double[]{0,1,1,0,0,0,0.343,0.099},
		};
		testdataoutput = new double[][]{
			new double[]{1},
			new double[]{1},
			new double[]{1},
			new double[]{0},
			new double[]{0},
			new double[]{0},
			new double[]{0},
		};
	}
	public static void main(String[] args){
		TrainData t = new TrainData();
		for(int i=0;i<t.traindata.length;i++){
			for(int j=0;j<9;j++)
				System.out.print(t.traindata[i][j]+ " ");
			System.out.println();
		}
	}
}
