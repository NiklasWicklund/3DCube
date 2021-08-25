import pygame
import numpy as np
import math

projMatrix = np.array([
        [1,0,0],
        [0,1,0]
    ])

class Point:
    def __init__(self,x,y):
        self.x = x
        self.y = y
    
    def tuple(self):
        return (self.x,self.y)


class Cube:
    def __init__(self,center,width):
        self.center = center
        self.width = width
        self.has_printed = False
        self.corners = [
            np.array([-0.5,-0.5,-0.5]),
            np.array([0.5,-0.5,-0.5]),
            np.array([0.5,0.5,-0.5]),
            np.array([-0.5,0.5,-0.5]),
            np.array([-0.5,-0.5,0.5]),
            np.array([0.5,-0.5,0.5]),
            np.array([0.5,0.5,0.5]),
            np.array([-0.5,0.5,0.5]),
        ]
        self.angle = 45
        self.rotXMatrix = [
            [1,0,0],
            [0,math.cos(self.angle),-math.sin(self.angle)],
            [0,math.sin(self.angle),math.cos(self.angle)]
        ]
    def updateRotMatrices(self):
        self.rotXMatrix = np.array([
            [1,0,0],
            [0,math.cos(self.angle),-math.sin(self.angle)],
            [0,math.sin(self.angle),math.cos(self.angle)]
        ])
        self.rotYMatrix = np.array([
            [math.cos(self.angle),0,-math.sin(self.angle)],
            [0,1,0],
            [math.sin(self.angle),0,math.cos(self.angle)]
        ])
    def render(self,pg,sc):
        self.angle += 0.001
        self.updateRotMatrices()
        color = (0,0,0)
        projCorners = []
        for v in self.corners:
            rotated = np.dot(self.rotXMatrix,v)
            rotated = np.dot(self.rotYMatrix,rotated)
            
            projected2d = np.dot(projMatrix,rotated)
            projCorners.append(Point(self.center[0] + projected2d[0]*self.width,self.center[1] + projected2d[1]*self.width))
        

        sides = [[0,1,2,3],[4,5,6,7],[0,3,7,4],[2,3,7,6],[1,2,6,5],[0,1,5,4]]
        blue = (30,144,255)
        for s in sides:
            pg.draw.polygon(sc,blue,list(map(lambda i: projCorners[i].tuple(),s)))
        
        for i in range(0,4):
            


            p1 = projCorners[i].tuple()
            p2 = projCorners[(i+1)%4].tuple()
            p3 = projCorners[(i+1)%4 + 4].tuple()
            p4 = projCorners[i+4].tuple()
            pg.draw.line(sc,color,p1,p2)
            pg.draw.line(sc,color,p4,p3)
            pg.draw.line(sc,color,p1,p4)
