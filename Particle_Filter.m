clear;
%Start setup
c1 = randi(100);
c2 = randi(100);
X = [c1-3 c1-3 c1+3 c1+3];
Y = [c2-1 c2+1 c2+1 c2-1];
rect = fill(X,Y,'c');
theta = randi(360);
rot = [cosd(theta) -sind(theta); sind(theta) cosd(theta)];
center = repmat([c1 c2], 4 ,1)';
axis([0 100 0 100]);
ax1 = gca;
V = get(rect,'Vertices')';
V = rot*(V-center)+center;
set(rect,'Vertices',V');
pause(0.1);
drawnow;
%end setup
%start number 2
goal = repmat([25 25], 4, 1)';
g = animatedline(ax1,'Marker','x');
addpoints(g,goal(1,1),goal(2,1));
squared = (goal-center).^2;
d = sqrt(squared(1,1) + squared(2,1)); %distance between position and goal
vd = 0;
xv = 0;
yv = 0;
n = 0;
kp = 2;
ki = 0.25;
kd = 0.1;
vref = 3;
previousError = 0;
integral = 0;
numGoals = 1;
goal2 = repmat([25 70], 4, 1)';
g2 = animatedline(ax1,'Marker','x');
addpoints(g2,goal2(1,1),goal2(2,1));
goal3 = repmat([70 25], 4, 1)';
g3 = animatedline(ax1,'Marker','x');
addpoints(g3,goal3(1,1),goal3(2,1));
g4 = animatedline(ax1,'Marker','x');
addpoints(g4,70,70);
g5 = animatedline(ax1,'Marker','x');
addpoints(g5,10,40);
g6 = animatedline(ax1,'Marker','x');
addpoints(g6,80,60);
x_init = 50;
y_init = 50;
theta_init = 90;

%creating particles
particles = zeros(1000,5); 
for c=1:1000
    particles(c,1) = randi(100); %x
    particles(c,2) = randi(100); %y
    particles(c,3) = randi(360); %theta
end

% %% goes into loop
% hold on;
% scatter(particles(1:1000,1),particles(1:1000,2),'.');
% hold off;
% 
% %distance of robot to landmarks simulated as sensor measurements with noise
% dl1 = sqrt((center(1,1)-25)^2+(center(2,1)-25)^2)+normrnd(0,sqrt(8));
% dl2 = sqrt((center(1,1)-25)^2+(center(2,1)-70)^2)+normrnd(0,sqrt(8));
% dl3 = sqrt((center(1,1)-70)^2+(center(2,1)-25)^2)+normrnd(0,sqrt(8));
% dl4 = sqrt((center(1,1)-70)^2+(center(2,1)-70)^2)+normrnd(0,sqrt(8));
% dl5 = sqrt((center(1,1)-10)^2+(center(2,1)-40)^2)+normrnd(0,sqrt(8));
% dl6 = sqrt((center(1,1)-80)^2+(center(2,1)-60)^2)+normrnd(0,sqrt(8));
% 
% %weighting
% landmarks = [25 25;25 70;70 25;70 70;10 40;80 60];
% robot_d = [dl1;dl2;dl3;dl4;dl5;dl6];    %sensor measurements
% w_sum = 0;
% for i=1:1000
%     prob_p = 0;
%     for lm =1:6
%         dist_i=sqrt((particles(i,1)-landmarks(lm,1))^2+(particles(i,2)-landmarks(lm,2))^2);
%         prob_p = prob_p + (1/sqrt(2*pi*0.5))*exp(-0.5*((dist_i-robot_d(lm,1))^2/0.5));
%     end
%     w_p = prob_p/6;
%     w_sum = w_sum + w_p;
%     particles(i,4) = w_p;
% end
% particles(1:1000,4) = particles(1:1000,4)./w_sum;
% 
% %resampling
% 
% % particles(1,5) = particles(1,4);
% % for i=2:1000
% %   particles(i,5) = particles(i,4)+particles(i-1,5); %creating the wheel
% % end
% 
% new_particles = [];
% r = (1/1000)*rand();
% c = particles(1,4);
% i = 1;
% for j=1:1000
%    U=r+((j-1)/1000);
%    while U > c
%       i = i + 1;
%       c = c + particles(i,4);
%    end
%    new_particles = cat(1, new_particles, particles(i,1:5));
% end
% 
% %move particles
% new_particles(1:1000,3) = new_particles(1:1000,3)+ radtodeg(0.2)+normrnd(0,sqrt(0.5));
% new_particles(1:1000,1) = 1*cos(new_particles(1:1000,3))+normrnd(0,sqrt(0.5));   %move particles 1m in the direction of their theta
% new_particles(1:1000,2) = 1*sin(new_particles(1:1000,3))+normrnd(0,sqrt(0.5));
% 
% particles = new_particles
figure(2);
while numGoals < 11
    s = scatter(particles(1:1000,1),particles(1:1000,2),'.');
    s.XDataSource = 'particles(1:1000,1)';
    s.YDataSource = 'particles(1:1000,2)';
    %distance of robot to landmarks simulated as sensor measurements with noise
    dl1 = sqrt((center(1,1)-25)^2+(center(2,1)-25)^2)+normrnd(0,sqrt(8));
    dl2 = sqrt((center(1,1)-25)^2+(center(2,1)-70)^2)+normrnd(0,sqrt(8));
    dl3 = sqrt((center(1,1)-70)^2+(center(2,1)-25)^2)+normrnd(0,sqrt(8));
    dl4 = sqrt((center(1,1)-70)^2+(center(2,1)-70)^2)+normrnd(0,sqrt(8));
    dl5 = sqrt((center(1,1)-10)^2+(center(2,1)-40)^2)+normrnd(0,sqrt(8));
    dl6 = sqrt((center(1,1)-80)^2+(center(2,1)-60)^2)+normrnd(0,sqrt(8));

    %weighting
    landmarks = [25 25;25 70;70 25;70 70;10 40;80 60];
    robot_d = [dl1;dl2;dl3;dl4;dl5;dl6];    %sensor measurements
    w_sum = 0;
    for i=1:1000
        prob_p = 0;
        for lm =1:6
            dist_i=sqrt((particles(i,1)-landmarks(lm,1))^2+(particles(i,2)-landmarks(lm,2))^2);
            prob_p = prob_p + (1/sqrt(2*pi*0.5))*exp(-0.5*((dist_i-robot_d(lm,1))^2/0.5));
        end
        w_p = prob_p/6;
        w_sum = w_sum + w_p;
        particles(i,4) = w_p;
    end
    particles(1:1000,4) = particles(1:1000,4)./w_sum;

    %resampling

    % particles(1,5) = particles(1,4);
    % for i=2:1000
    %   particles(i,5) = particles(i,4)+particles(i-1,5); %creating the wheel
    % end

    new_particles = [];
    r = (1/1000)*rand();
    c = particles(1,4);
    i = 1;
    for j=1:1000
        U=r+((j-1)/1000);
        while U > c
            i = i + 1;
            c = c + particles(i,4);
        end
        new_particles = cat(1, new_particles, particles(i,1:5));
    end
    
    dtheta = 0.2 + normrnd(0,sqrt(0.5));
    theta = theta + dtheta;
    xv = 1.*cos(theta)+normrnd(0,sqrt(0.5));   %move robot 1m in direction of current orientation
    yv = 1.*sin(theta)+normrnd(0,sqrt(0.5));
        
    c1 = c1 + xv;
    c2 = c2 + yv;
    X = [c1-3 c1-3 c1+3 c1+3];
    Y = [c2-1 c2+1 c2+1 c2-1];
    hold on;
    rect = fill(X,Y,'c');
    center = repmat([c1 c2], 4 ,1)';
    axis([0 100 0 100]);
    rot3= [cos(theta) -sin(theta); sin(theta) cos(theta)];
    V = rot3*(V-center)+center;
    set(rect,'Vertices',V');
    h = animatedline(ax1,'Marker','.');
    h.Color = 'red';
    addpoints(h,center(1,1),center(2,1));
    hold off;
    numGoals = numGoals + 1;
       
    %move particles
    for i=1:1000
        new_particles(i,3) = new_particles(i,3)+ radtodeg(0.2)+ normrnd(0,sqrt(0.5));
        new_particles(i,1) = new_particles(i,1)+1*cos(new_particles(i,3))+normrnd(0,sqrt(0.5));   %move particles 1m in the direction of their theta
        new_particles(i,2) = new_particles(i,2)+1*sin(new_particles(i,3))+normrnd(0,sqrt(0.5));
    end
    particles = new_particles;
    
    pause(1);

    refreshdata
end
%end number 2