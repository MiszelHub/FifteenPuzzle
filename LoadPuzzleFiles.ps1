
param
(
   [Parameter(Mandatory = $true)][string] $directory,
   [Parameter(Mandatory = $true)][ValidateSet("bfs","dfs","astr")][string] $algorithm,
   [Parameter(Mandatory = $false)][string] $jarPath = "C:\Users\user\Desktop\Repozytoria\FifteenPuzzle\target\Fifteenpuzzle-1.0-SNAPSHOT-jar-with-dependencies.jar",
   [Parameter(Mandatory = $false)][string] $outputDirectory = "C:\Users\user\Desktop\Out\"
)

$fileRegex ="[a-zA-Z0-9]+_[0-9]+_[0-9]+.txt";

$files = Get-ChildItem $directory | Where-Object {$_.Name -match $fileRegex}
$strategies = @("RDUL","RDLU","DRUL","DRLU","LUDR","LURD","ULDR","ULRD");
$heuristcs = @("hamm","manh");


if($algorithm -eq "astr")
{
    foreach($heuristic in $heuristcs)
    {
         foreach($file in $files)
         {

            $app = Start-Process java -ArgumentList '-jar',$jarPath, $file.FullName, $algorithm, $heuristic, $outputDirectory -PassThru -NoNewWindow 
                
            $app.PriorityClass = "High"
            $app.WaitForExit()
        
         }
    }
}
else
{
    foreach($strategy in $strategies)
    {
        foreach($file in $files)
        {
           #$file
           $app = Start-Process java -ArgumentList '-jar',$jarPath, $file.FullName, $algorithm, $strategy, $outputDirectory -PassThru -NoNewWindow
           
           $app.PriorityClass = "High"
          
           $app.WaitForExit()

        }
     }
}


pause
