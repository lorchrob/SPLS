spls32s = readSpls32s();
spls32s = sortSpls32s(spls32s);
examples = splsSieve(spls32s);

%{
TO DO:

Verify that sorting leads to the same spls32s, just in a different order
Verify that we get the same examples with a different arrayLog
%}
