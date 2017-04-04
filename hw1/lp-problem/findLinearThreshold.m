% This function solves the LP problem for a given weight vector
% to find the threshold theta.
% YOU NEED TO FINISH IMPLEMENTATION OF THIS FUNCTION.

function [theta,delta] = findLinearThreshold(data,w)
%% setup linear program
[m, np1] = size(data);
n = np1-1;

% write your code here
A= zeros(m+1,np1+1);
for i = 1:m
    A(i,1:n)= data(i,1:n) * data(i,np1);
    A(i,np1)= data(i,np1);
    A(i,np1+1) = 1;
end
A(m+1,np1+1) = 1;
c=zeros(1,np1+1);
c(1,np1+1) = 1;
b= ones(1,m+1);
b(m+1) = 0;


%% solve the linear program
%adjust for matlab input: A*x <= b
[t, z] = linprog(c, -A, -b, [], [], [w' -inf -inf], [w' inf inf]);


%% obtain w,theta,delta from t vector
w = t(1:n);
theta = t(n+1);
delta = t(n+2);

end
