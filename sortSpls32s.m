function sorted = sortSpls32s(spls32s)
  sorted = reshape(spls32s, [36, 1936]);
  sorted = sorted';
  sorted = sortrows(sorted);
  sorted = sorted';
  sorted = reshape(sorted, [6, 6, 1936]);
end