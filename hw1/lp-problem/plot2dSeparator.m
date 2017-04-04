% This function plots the linear discriminant.
% YOU NEED TO IMPLEMENT THIS FUNCTION

function plot2dSeparator(w, theta)
    % plot line
    % find 0 = wT [x y] + theta
    x = -0.1 :0.1:1.1;
    y = (-w(1) * x - theta) / w(2);
    hold on
    plot(x,y);
    % adjusting axis
    axis([-0.1 1.1 -0.1 1.1]);
end
