%{
Applies specified row operations to spls32 to generate and return
newSpls32. 'num' specifies which operations to do:
  0 : identity (do nothing)
  1 : flip (r_2)
  2 : swap first two rows (r_0)
  3 : swap last two rows (r_1)
  4 : swap first two and last two rows (r_0 and r_1)
  5 : case 2, but with a flip (r_0, r_2)
  6 : case 3, but with a flip (r_1, r_2)
  7 : case 4, but with a flip (r_0, r_1, r_2)
%}
function newSpls32 = rowOps(num, spls32)
  % define matrices we multiply by to perform row operations
  r_0 = [0 1 0 0 0 0; 1 0 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 1 0; 0 0 0 0 0 1];
  r_1 = [1 0 0 0 0 0; 0 1 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 0 1; 0 0 0 0 1 0];
  r_0_and_r_1 = [0 1 0 0 0 0; 1 0 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 0 1; 0 0 0 0 1 0];
  
  switch num
    % identity
    case 0
      newSpls32 = spls32;
    
    % r_2
    case 1
      newSpls32 = flip(spls32);
      
    % r_0  
    case 2
      newSpls32 = r_0 * spls32;
      
    % r_1  
    case 3
      newSpls32 = r_1 * spls32;
      
    % r_0, r_1
    case 4
      newSpls32 = r_0_and_r_1 * spls32;
      
    % r_0, r_2
    case 5
      newSpls32 = flip(spls32);
      newSpls32 = r_0 * newSpls32;
      
    % r_1, r_2
    case 6
      newSpls32 = flip(spls32);
      newSpls32 = r_1 * newSpls32;
      
    % r_0, r_1, r_2
    case 7
      newSpls32 = flip(spls32);
      newSpls32 = r_0_and_r_1 * newSpls32;
  end
end