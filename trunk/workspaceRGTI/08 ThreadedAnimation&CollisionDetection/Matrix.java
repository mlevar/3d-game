
public class Matrix {
	private double[][] values;
	
	public Matrix(double[][] v) {
		if (check(v))
			values = v.clone();
		else {
			System.err.println("The matrix array is not well defined.");
			System.exit(1);
		}
	}
	
	private static boolean check(double[][] v) {
		if (v.length == 0)
			return false;
		
		int dim=v[0].length;
		for (int i=1; i<v.length; i++)
			if(v[i].length != dim)
				return false;
		
		return true;
	}
	
	public static Matrix unimatrix(int dim) {
		Matrix m = new Matrix(new double[dim][dim]);
		for (int i=0; i<dim; i++)
			m.setValue(i, i, 1);
		return m;
	}
	
	public static Matrix negate(Matrix a) {
		return multiply(-1, a);
	}
	
	public static Matrix add(Matrix a, Matrix b) {
		if (a.getDimension()[0] != b.getDimension()[0] || a.getDimension()[1] != b.getDimension()[1]) {
			System.err.println("Matrix dimensions do not agree!");
			return null;
		}
		Matrix m = new Matrix(new double[a.getDimension()[0]][a.getDimension()[1]]);
		for (int i=0; i<a.getDimension()[0]; i++)
			for (int j=0; j<a.getDimension()[1]; j++)
				m.setValue(i, j, a.getValue(i, j) + b.getValue(i, j));
		return m;
	}
	
	public static Matrix transpose(Matrix a) {
		Matrix m = new Matrix(new double[a.getDimension()[1]][a.getDimension()[0]]);
		for (int i=0; i<m.getDimension()[0]; i++)
			for (int j=0; j<m.getDimension()[1]; j++)
				m.setValue(i, j, a.getValue(j, i));
		return m;
	}
	
	public static Matrix multiply(double d, Matrix a) {
		Matrix m = new Matrix(new double[a.getDimension()[0]][a.getDimension()[1]]);
		for (int i=0; i<a.getDimension()[0]; i++)
			for (int j=0; j<a.getDimension()[1]; j++)
				m.setValue(i, j, d*a.getValue(i, j));
		return m;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public static Matrix multiply(Matrix a, Matrix b) {
		if (a.getDimension()[1] != b.getDimension()[0]) {
			System.err.println("Matrix dimensions do not agree!");
			return null;
		}
		Matrix m = new Matrix(new double[a.getDimension()[0]][b.getDimension()[1]]);
		
		for (int i=0; i<a.getDimension()[0]; i++)
			for (int j=0; j<b.getDimension()[1]; j++)
				for (int k=0; k<a.getDimension()[1]; k++)
					m.setValue(i, j, m.getValue(i, j) + a.getValue(i, k) * b.getValue(k, j));
		
		return m;
	}
	
	public void setValue(int row, int col, double value) {
		values[row][col] = value;
	}
	
	public double getValue(int row, int col) {
		return values[row][col];
	}
	
	public int[] getDimension() {
		return new int[] {this.values.length, this.values[0].length};
	}
		
	@Override
	public String toString() {
		String out = "";
		for(double[] row : values) {
			out += "[";
			for(double v : row)
				out += String.format("%6.2f", v) + "\t";
			out = out.substring(0, out.length()-1);
			out += "]\n";
		}
		
		return out;		
	}
	
	public double[][] getMatrix() {
		return values;
	}
}
