function bool = isRelabeling(spls32, newSpls32)
  % define relabeling array
  relabel(1) = newSpls32(3, 1);
  relabel(2) = newSpls32(3, 2);
  relabel(3) = newSpls32(3, 3);
  relabel(4) = newSpls32(4, 1);
  relabel(5) = newSpls32(4, 2);
  relabel(6) = newSpls32(4, 3);
          
  % relabel spls32
  for y = 1:6
    for z = 1:6
      spls32(y, z) = relabel(spls32(y,z) + 1);
    end
  end
        
  % figure out if newSpls32 is a relabeling of spls32
  diff = spls32 - newSpls32;
  diff = abs(diff);
  diff = sum(diff);
  diff = sum(diff);
          
  if diff == 0
    bool = 1;
  else
    bool = 0;
  end
end