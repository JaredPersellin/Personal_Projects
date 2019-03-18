function resetAll(){
document.getElementById("max").value = "";
document.getElementById("mode").value = "";
document.getElementById("std").value = "";
document.getElementById("var").value = "";
document.getElementById("textInput").value = "";
document.getElementById("min").value = "";
document.getElementById("sum").value = "";
document.getElementById("mean").value = "";
document.getElementById("median").value = "";
}

function performStatistics(){
var xValue = document.getElementById("textInput").value;
values = xValue.split(/\s+/);
for(i=0;i<values.length;i++){
values[i] = parseInt(values[i].trim());
if ((values[i]>100) || (values[i]<0)){
	document.getElementById("textInput").value = "Valid Numbers are 0 to 100";
	throw new Error("Invalid numbers");
}
}
if ((values.length>20) || (values.length<5)){
	document.getElementById("textInput").value = "Please enter between 5 and 20 numbers";
	throw new Error("To many or few numbers");
}
document.getElementById("min").value = findMin(values);
document.getElementById("max").value = findMax(values);
document.getElementById("sum").value = calcSum(values);
document.getElementById("mean").value = calcMean(values);
document.getElementById("median").value = calcMedain(values);
document.getElementById("mode").value = calcMode(values);
document.getElementById("std").value = calcStdDev(values);
document.getElementById("var").value = calcVariance(values);
return false;
}



function calcVariance(values){
mean=calcMean(values);
temp = 0;
for(i=0;i<values.length;i++){
temp += Math.pow(values[i]-mean,2);
}
varcCalc = temp/values.length;
return varcCalc.toFixed(2);
}

function calcStdDev(values){
varc = calcVariance(values);
varcf = Math.pow(varc,1/2);
return varcf.toFixed(2);
}

function calcMode(values){
var count=0;
maxCount = 0;
for(i=0;i<values.length;i++){
counter=0;
for(j=i+1;j<values.length;j++){
if(values[i]==values[j]){
counter++;
}
}
if(counter>maxCount){
maxCount=counter;
}
}
list="";
for(i=0;i<values.length;i++){
counter=0;
for(j=i+1;j<values.length;j++){
if(values[i]==values[j]){
counter++;
}
}
if(counter==maxCount){
list+=values[i]+" ";
}
}
return list;
}

function calcMedain(values){
for(i=0;i<values.length;i++){
for(j=i+1;j<values.length;j++){
if(values[i]>values[j]){
temp = values[i];
values[i] = values[j];
values[j] =temp;
}
}
}
numsLen = values.length;
var median=0;
if (numsLen % 2 === 0) {
median = (values[numsLen / 2 - 1] + values[numsLen / 2]) / 2;
} else { 
median = values[(numsLen - 1) / 2];
}
return median.toFixed(2);
}

function calcMean(values){
sum = calcSum(values);
meanF = sum/values.length;
return meanF.toFixed(2);
}

function calcSum(values){
var total = 0;
for(i=0;i<values.length;i++){
total+=values[i];
}
return total.toFixed(2);
}

function findMin(values){
var min = 101;
for(i=0;i<values.length;i++){
if(values[i]<min){
min = values[i];
}
}
return min.toFixed(2);
}

function findMax(values){
var maxx = 0;
for(i=0;i<values.length;i++){
if(values[i]>maxx){
maxx = values[i];
}
}
return maxx.toFixed(2);
}