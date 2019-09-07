%{
Applies specified transpose operations to spls32 to generate and return
newSpls32. 'num' specifies which operations to do:
  0 : identity (do nothing)
  1 : transpose 
%}
function newSpls32 = transposeOps(num, spls32)
  if num
    newSpls32 = transpose(spls32);
  else
    newSpls32 = spls32;
  end
end