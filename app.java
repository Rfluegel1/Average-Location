import java.util.Scanner;

JSONObject json;
ArrayList<Point> points = new ArrayList<Point>();
PImage map = loadImage("https://i.imgur.com/3HXcPlB.jpg");
double aLat = -1, aLon = -1;

void setup(){
  map.resize(1600,900);
  size(1600, 900);
}

void draw(){
  background(map);
  drawPoints();
  if(points.size() >= 2){
    placeAverage();
  }
}

class Point{
  float x;
  float y;
  color c;
  boolean toDelete = false;
  Point(double lon, double lat, color col){
    x = (float)lon;
    y = (float)lat;
    c = col;
  }
  float getLat(){
    return y;
  }
  float getLon(){
    return x;
  }
  void show(){
    fill(c);
    ellipse(x, y, 10, 10);
  }
}

void placeAverage(){
  double tLat = 0, tLon = 0;
  for(int i= 0; i < points.size(); i++){
    tLat += points.get(i).getLat();
    tLon += points.get(i).getLon();
  }
  aLat = tLat/points.size();
  aLon = tLon/points.size();
  fill(#0000FF);
  ellipse((float)aLon, (float)aLat, 10, 10);
}

void drawPoints(){
  for(int i = 0; i < points.size(); i++){
    points.get(i).show();
  }
}

void mouseClicked(){
  points.add(new Point(mouseX, mouseY, #FF7F50));
  //json = loadJSONObject("https://www.google.com/maps/search/?api=1&parameters");
  println("Added new point");
  println("Coordinates:");
  println("Latitude: " + calculateLat(mouseY) + " Longitude: " + calculateLon(mouseX));
}

void keyPressed(){
  if (key == CODED){
    println("Average Coordinates:");
    double lat = calculateLat(aLat);
    double lon = calculateLon(aLon);
    println("Latitude: " + lat + " Longitude: " + lon); 
    //json = loadJSONObject("https://www.google.com/maps/search/?api=1&query=" + lat + "," + lon);
    
  }
}

double calculateLon(double Lon){
  double lon = ((Lon/1600-1)*57.71)-66.95;
  return lon;
}

double calculateLat(double Lat){
  double lat = -1*(((Lat/900-1)*24.82)-24.55);
  return lat;
}
