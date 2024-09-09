//package;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class ItemGenerator {
  public static class Item {
    public String name;
    public int stock;
    public double price;

    public Item (String n, int stockItem, double priceItem)
    { name=n; stock=stockItem; price=priceItem; }

    public String toString () {
      return name + "," + stock + "," + price;
    }
  }

  public static void main( String [] args ) throws IOException {
    Item [] itemArr = new Item[23];  // default 23 possible Item types
    File inputFile = new File( "./ItemDetail.txt" );
    Scanner scan = new Scanner( inputFile );
    int count=0;
    while ( scan.hasNext( ) ) {
      StringTokenizer stringToken = new StringTokenizer(scan.nextLine());
      itemArr[count] = new Item(stringToken.nextToken(","),
      		Integer.parseInt(stringToken.nextToken(",")),
      		Double.parseDouble(stringToken.nextToken(",")));
      count++;
    }
/*
output a comma delimited file:
Name,stock,price
*/
    try
    {
      FileWriter fw = new FileWriter( "./stock.txt", false);
      BufferedWriter bw = new BufferedWriter( fw );
      int ItemCount=(int)(Math.random()*6)+15;   //generate random number 15-20
      int[] visited = new int[23];
      for (int i = 0; i < ItemCount; i++) {
	int index=(int)(Math.random()*count);  // pick an Item randomly
	if (visited[index] == 1) {
	    i--;
	    continue;
	}
          bw.write(itemArr[index].name);
          bw.write( "," );		
          bw.write(String.valueOf(itemArr[index].stock));
	bw.write( "," );									
          bw.write(String.valueOf(itemArr[index].price));
	bw.newLine( );
          bw.flush();
	visited[index] = 1;
      }
      bw.close();
    }
    catch( IOException ioe )
    {
      ioe.printStackTrace( );
    }

    scan.close();
    
  }
}