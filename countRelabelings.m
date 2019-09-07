%{
NOTE: Must run 'readSpls32s' first!

Purpose:
  * Counts the number of essentially "unique" variants
    of each spls32 by applying 128 operations and seeing if the resulting
    spls32s are relabelings (applies these 128 operations to each spls32)

Parameters:
  * spls32s, a 3d array of all spls32s

Returns:
  * counts, an array of the unique symmetries of all the spls32s (first
    count applies to first spls32, etc.)
%}

function counts = countRelabelings(spls32s)
  counts = zeros(1, 1936);
  for i = 1:1936
    spls32 = spls32s(:,:,i);
    for j = 0:7
      for k = 0:7
        for x = 0:1        
          newSpls32 = rowOps(j, columnOps(k, transposeOps(x, spls32)));
          counts(i) = counts(i) + isRelabeling(spls32, newSpls32);         
        end
      end
    end
  end
end