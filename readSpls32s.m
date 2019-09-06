function Spls32s = readSpls32s()
  fileID = fopen('arrayLog.txt', 'r');
    
  % pre-allocate space for efficiency
  Spls32s = zeros(6, 6, 1936);
    
  % load in arrays with multiple calls to 'fscanf'
  for i = 1:1936
      Spls32s(:,:,i) = fscanf(fileID, '%d', [6, 6]);
  end
    
  Spls32s = permute(Spls32s, [2 1 3]);
  
  fclose(fileID);
end