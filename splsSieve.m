%{
Purpose:
  * to find 26 spls32s that a representative examples of their orbits

Parameters:
  * spls32s, a 3d matrix of all the spls32s

Returns:
  * examples, a cell array of all the representative example spls32s

NOTE: mark array stores "mark" for each spls32:
        * 0 = unvisited
        * 1 = crossed out
        * 2 = circled
      at the end, all spls32s are marked at 1 or 2, with (theoretically) 26
      of them "circled"
%}
function examples = splsSieve(spls32s)
  marks = zeros(1, 1936);
  z = 1;
  
  for i = 1:1936
    if marks(i) ~= 1
      spls32 = spls32s(:,:,i);
      
      % if we get to this spls, we circle it and add it to our cell array
      marks(i) = 2;
      examples{z} = spls32;
      z = z + 1;
      
      % for each spls32, we undergo all 128 symmetries
      for j = 0:7
        for k = 0:7
          for x = 0:1        
            newSpls32 = rowOps(j, columnOps(k, transposeOps(x, spls32)));
            
            % for each symmetry, we cross out all the spls32s further in
            % the 3d array that are a relabeling
            for y = (i + 1):1936
              if isRelabeling(spls32s(:,:,y), newSpls32);
                marks(y) = 1;
              end
            end
          end
        end
      end
    end
  end
end