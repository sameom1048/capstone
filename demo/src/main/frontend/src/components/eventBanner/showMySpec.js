const os = require('os');

const cpuName = os.cpus()[0].model;
console.log(cpuName);