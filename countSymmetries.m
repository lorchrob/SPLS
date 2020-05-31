%{
NOTE: Must run 'readSpls32s' first!

Purpose:
  * For each spls32 with fixed center-left block, we counts the number of
    physical symmetries (out of 128) that result in a relabeling of the
    given spls32.

Parameters:
  * spls32s, a 3d array of all spls32 with fixed center-left block.

Returns:
  * counts, an array of the number of unique physical symmetries resulting
    in relabelings. The first
    count applies to first spls32, etc.
%}

function counts = countSymmetries(spls32s)
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