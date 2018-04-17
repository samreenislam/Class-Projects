close all;
clear;
%Start setup
x = 0:1:100;
y = 0:1:100;
[X,Y] = meshgrid(x,y);
dAttract=sqrt((X-80).^2+(Y-20).^2);
figure
contour(X,Y,dAttract);
[DX,DY] = gradient(dAttract);
hold on
h=quiver(X,Y,-DX,-DY);
hold off
square=zeros(101);
% quad stuff
for ik=1:101
    for jk=1:101
        if (ik <= 30)
            if(jk <= 50)
                dRepel=10000.*((1./(sqrt((ik-30).^2+(jk-50).^2)))+(1./(sqrt(200))));
            elseif(jk > 50 && jk < 70)
                dRepel=10000.*((1./(sqrt((ik-30).^2)))+(1./(sqrt(200))));
            else
                dRepel=10000.*((1./(sqrt((ik-30).^2+(jk-70).^2)))+(1./(sqrt(200))));
            end
        end
        if (ik >= 50)
            if(jk <= 50)
                dRepel=10000.*((1./(sqrt((ik-50).^2+(jk-50).^2)))+(1./(sqrt(200))));
            elseif(jk > 50 && jk < 70)
                dRepel=10000.*((1./(ik-50))+(1./(sqrt(200))));
            else
                dRepel=10000.*((1./(sqrt((ik-50).^2+(jk-70).^2)))+(1./(sqrt(200))));
            end
        end
        if (ik >= 30 && ik <=50 && jk >= 70)
            dRepel=10000.*((1./(jk-70))+(1./(sqrt(200))));
        end
        if (ik >= 30 && ik <= 50 && jk <= 50)
            dRepel=10000.*((1./(sqrt((jk-50).^2)))+(1./(sqrt(200))));
        end
        if (ik >= 30 && ik <= 50 && jk >= 50 && jk <= 70)
            maxim = max(square);
            dRepel = max(maxim);
        end
        square(jk,ik)=dRepel;
    end
end
figure
axis([0 100 0 100]);
XSquare = [30 30 50 50];
YSquare = [50 70 70 50];
XYSquare = fill(XSquare,YSquare,'c');
hold on
contour(X,Y,square);
hold off
[ux,uy] = gradient(square,100,100);
%hold on
%uv=quiver(X,Y,-ux,-uy,0);
%hold off

figure
dBoth = 250.*dAttract + square;
surf(X,Y,dBoth);
figure
contour(X,Y,dBoth);
[UX,UY] = gradient(dBoth,100,100);
%hold on
%uv=quiver(X,Y,-UX,-UY,0);
%hold off
t = animatedline('LineWidth',2);
t.Color = 'red';

Xr = [16 16 24 24];
Yr = [86 94 94 86];
hold on
rect = fill(Xr,Yr,'m');
theta = randi(360);
rot = [cosd(theta) -sind(theta); sind(theta) cosd(theta)];
xc = 20;
yc = 90;
center = repmat([xc yc], 4 ,1)';
V = get(rect,'Vertices')';
V = rot*(V-center)+center;
set(rect,'Vertices',V');
drawnow;
hold off
d = sqrt((80-xc).^2+(20-yc).^2);
hold on
g = plot(80,20,'x','MarkerFaceColor','blue');
hold off
addpoints(t,xc,yc);
goal = repmat([80 20], 4, 1)';
goalLine = animatedline('Marker','.');
addpoints(goalLine,xc,yc);
squared = (goal-center).^2;
distance = sqrt(squared(1,1) + squared(2,1));
vd = 5;
xv = 0;
yv = 0;
while distance > 1
    squared = (goal-center).^2;
    distance = sqrt(squared(1,1) + squared(2,1)); %distance between position and goal
    
    thetaE = atan2(UY(round(center(2,1)),round(center(1,1))),UX(round(center(2,1)),round(center(1,1))));
    thetaCurr = atan2(V(2,2)-V(2,3),V(1,2)-V(1,3));
    dtheta =(thetaE-thetaCurr)*0.1;
    if abs(dtheta) > pi/4
        if dtheta > 0
            dtheta = pi/4;
        else
            dtheta = -pi/4;
        end
    end
    if abs(dtheta) > 0
        xv=-vd.*cos(thetaCurr).*0.1;
        yv=-vd.*sin(thetaCurr).*0.1;
        rot3= [cos(dtheta) -sin(dtheta); sin(dtheta) cos(dtheta)];
    else
        xv = abs(UX(round(center(2,1)),round(center(1,1))))*0.1;
        yv = abs(UY(round(center(2,1)),round(center(1,1))))*0.1;
        rot3 = 1;
    end
    dmove = repmat([xv yv], 4, 1)';
    V = rot3*(V-center)+center+dmove;
    center = center+dmove;
    set(rect,'Vertices',V');
    addpoints(goalLine,center(1,1),center(2,1));
     
    pause(0.1);
    drawnow;
end