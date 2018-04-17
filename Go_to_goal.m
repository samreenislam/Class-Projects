clear;
%Start setup
X = [94 94 106 106];
Y = [96 104 104 96];
rect = fill(X,Y,'c');
theta = randi(360);
rot = [cosd(theta) -sind(theta); sind(theta) cosd(theta)];
center = repmat([100 100], 4 ,1)';
axis([0 200 0 200]);
ax1 = gca;
V = get(rect,'Vertices')';
V = rot*(V-center)+center;
set(rect,'Vertices',V');
drawnow;
%end setup
%start number 1
funky = repmat([19 9], 4, 1)';
rot2 = [cos(pi/13) -sin(pi/13); sin(pi/13) cos(pi/13)];
V = rot2*(V-center)+center+funky;
center = center+funky;
set(rect,'Vertices',V');
drawnow;
%end number 1
%start number 2
goal = repmat([randi(200) randi(200)], 4, 1)';
g = animatedline(ax1,'Marker','x');
addpoints(g,goal(1,1),goal(2,1));
squared = (goal-center).^2;
d = sqrt(squared(1,1) + squared(2,1)); %distance between position and goal
vd = 0;
ax1_pos = ax1.Position;
ax2 = axes('Position',ax1_pos,...
    'XAxisLocation','top',...
    'YAxisLocation','right',...
    'Color','none');
n = 0;
while d > 0.1
    squared = (goal-center).^2;
    d = sqrt(squared(1,1) + squared(2,1)); %distance between position and goal
    if 0.75*d > 5
        vd = vd + 0.1;
    else 
        vd= 2*d; %velocity
    end
    thetaE = atan2(goal(2,1)-center(2,1),goal(1,1)-center(1,1));
    thetaCurr = atan2(V(2,2)-V(2,3),V(1,2)-V(1,3));
    dtheta =(thetaE-thetaCurr)*0.1;
    if abs(dtheta) > pi/4
        if dtheta > 0
            dtheta = pi/4;
        else
            dtheta = -pi/4;
        end
    end
    xv=vd.*cos(thetaCurr).*0.1;
    yv=vd.*sin(thetaCurr).*0.1;
    dmove = repmat([xv yv], 4, 1)';
    rot3= [cos(dtheta) -sin(dtheta); sin(dtheta) cos(dtheta)];
    V = rot3*(V-center)+center+dmove;
    center = center+dmove;
    set(rect,'Vertices',V');
    h = animatedline(ax1,'Marker','.');
    h.Color = 'red';
    addpoints(h,center(1,1),center(2,1));
    w = animatedline(ax2, 'Marker','.');
    w.Color = 'blue';
    addpoints(w, 0.1.*n, vd);
    n = n+1;
    pause(0.1);
    drawnow;
end
%end number 2