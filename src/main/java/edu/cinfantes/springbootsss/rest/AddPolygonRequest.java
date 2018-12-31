package edu.cinfantes.springbootsss.rest;

import lombok.Data;

@Data
class AddPolygonRequest {
  private Coordinates coordinates1 = new Coordinates();
  private Coordinates coordinates2 = new Coordinates();
  private Coordinates coordinates3 = new Coordinates();
  private Coordinates coordinates4 = new Coordinates();
}

@Data
class Coordinates{
  private int x;
  private int y;
}
