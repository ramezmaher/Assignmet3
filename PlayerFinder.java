package eg.edu.alexu.csd.datastructure.iceHockey.cs29;
import java.awt.Point;
import java.util.*;

public class PlayerFinder implements IPlayersFinder { 
	
	public boolean safe(int y,int x,int l,int m,int[][] visited) {
		if ((y>=0)&&(x>=0)&&(y<l)&&(x<m)&&(visited[y][x] == 0)) {
			return true;
		}
		else 
			return false;
			
	}
	public int[][] loop (boolean[][] b,int y,int x,int l, int m,int[][] visited,int num) {
				if (safe(y,x,l,m,visited)) {
					if (b[y][x] == true) {
					visited[y][x] = num;
					visited=loop(b,y,x+1,l,m,visited,num);
					visited=loop(b,y+1,x,l,m,visited,num);
					visited=loop(b,y,x-1,l,m,visited,num);
					visited=loop(b,y-1,x,l,m,visited,num);
					}
					else {
						visited[y][x]=1;
					}
			}
		return visited;
		}
	public void  sortArray(Point[] arr) {
		for (int i =0 ; i<arr.length ;i++) {
			for (int j=i+1;j<arr.length;j++) {
				if (arr[i].x > arr[j].x) {
					Point p =new Point();
					p=arr[i];
					arr[i]=arr[j];
					arr[j]=p;
				}
				else if (arr[i].x == arr[j].x) {
					if (arr[i].y == arr[j].y) {
						Point p =new Point();
						p=arr[i];
						arr[i]=arr[j];
						arr[j]=p;
					}
				}
			}

		}
		return;
	}
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		ArrayList<Integer> Arr =new ArrayList<Integer>();
		char c = Character.forDigit(team, 10);
	    int l=photo.length;//Y-axis
		int m=photo[0].length();//X-axis
		int num=2;
		boolean b[][] = new boolean[l][m];
		int visited[][] = new int[l][m];
		for (int i=0; i<l;i++) {
			for (int j = 0 ; j<m;j++) {
				if (photo[i].charAt(j) == c) {
					b[i][j] = true ;
				}
				else b[i][j] = false;
			}
		}
		for (int y=0;y<l;y++) {
			for (int x=0;x<m;x++) {
		visited=loop(b,y,x,l,m,visited,num);
		num++;
			}
		}
		boolean z =false;
		int u=0;{
			for(int i=0;i<l;i++) {
				for (int j=0;j<m;j++) {
					if (visited[i][j] != 1) {
						if (Arr.isEmpty()) {
							u++;
							Arr.add(visited[i][j]);
						}
						else {for (int d=0; d<Arr.size();d++) {
							if (Arr.get(d) == visited[i][j]) {
								 z =true;
							}
						}
						if (z ==false) {
							u++;
							Arr.add(visited[i][j]);
						}

					}
					}
					z=false;
				}
			}
		}
	for (int k =0 ; k<u;k++) {
		int count = 0;
		for (int j=0;j<l;j++) {
			for (int i=0;i<m;i++) {
				if(visited[j][i] ==Arr.get(k))
					count++;
			}
		}if ((count*4)<threshold) {
			u--;
			for (int j=0;j<l;j++) {
				for (int i=0;i<m;i++) {
					if(visited[j][i] == Arr.get(k)) {
						visited[j][i]=1; 
					}
		     }
	      }
			Arr.remove(k);
		}
	}
	Point[] P = new Point[u];
	int o=0;
	int maxX=0, minX=m,maxY=0,minY=l;
	for (int k=0;k<u;k++) {
		        maxX=0;
				minX=m;
				maxY=0;
				minY=l;
		for (int j=0;j<l;j++) {
			for (int i=0;i<m;i++) {
				if (visited[j][i]==Arr.get(k)) {
					if (i>maxX) {
						maxX=i;
					}
					 if (i<minX) {
						minX=i;
					}
					if (j>maxY) {
						maxY=j;
					}
					if (j<minY) {
						minY=j;
					}
				}
			}
		}
		P[o]= new Point();
		P[o].x=(minX+maxX+1);
		P[o].y=(minY+maxY+1);
		o++;
	}
	sortArray(P);
	return P;
}
}
