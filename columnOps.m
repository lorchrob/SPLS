%{
Applies specified column operations to spls32 to generate and return
newSpls32. 'num' specifies which operations to do:
  0 : identity (do nothing)
  1 : flip (c_2)
  2 : swap first two columns (c_0)
  3 : swap last two columns (c_1)
  4 : swap first two and last two columns (c_0 and c_1)
  5 : case 2, but with a flip (c_0, c_2)
  6 : case 3, but with a flip (c_1, c_2)
  7 : case 4, but with a flip (c_0, c_1, c_2)
%}
function newSpls32 = columnOps(num, spls32)
  % define matrices we multiply by to perform column operations
  c_0 = [0 1 0 0 0 0; 1 0 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 1 0; 0 0 0 0 0 1];
  c_1 = [1 0 0 0 0 0; 0 1 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 0 1; 0 0 0 0 1 0];
  c_0_and_c_1 = [0 1 0 0 0 0; 1 0 0 0 0 0; 0 0 1 0 0 0; 0 0 0 1 0 0; 0 0 0 0 0 1; 0 0 0 0 1 0];
  
  switch num
    % identity
    case 0
      newSpls32 = spls32;
    
    % c_2
    case 1
      newSpls32 = fliplr(spls32);
      
    % c_0  
    case 2
      newSpls32 = spls32 * c_0;
      
    % c_1  
    case 3
      newSpls32 = spls32 * c_1;
      
    % c_0, c_1
    case 4
      newSpls32 = spls32 * c_0_and_c_1;
      
    % c_0, c_2
    case 5
      newSpls32 = fliplr(spls32);
      newSpls32 = newSpls32 * c_0;
      
    % c_1, c_2
    case 6
      newSpls32 = fliplr(spls32);
      newSpls32 = newSpls32 * c_1;
      
    % c_0, c_1, c_2
    case 7
      newSpls32 = fliplr(spls32);
      newSpls32 = newSpls32 * c_0_and_c_1;
  end
end