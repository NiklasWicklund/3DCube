import pygame
import numpy as np
import cube


class Vector:
    def __init__(self,x,y):
            self.x = x
            self.y = y
    def __init__(self,x,y,z):
            self.x = x
            self.y = y
            self.z = z


(width,height) = (450,450)
screen = pygame.display.set_mode((width,height))
pygame.display.set_caption('Spinning Cube')
screen.fill((255,255,255))
pygame.display.flip()

running = True

cube = cube.Cube((width/2,height/2,0),100)

while running:
    screen.fill((255,255,255))
    cube.render(pygame,screen)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    pygame.display.update()

pygame.quit()