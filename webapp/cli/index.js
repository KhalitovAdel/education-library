#!/usr/bin/env node
/* eslint-disable no-undef */

const { Command } = require('commander');

//  Точка входа в терминальное приложение
const program = new Command();

program
  .name('library')
  .description('Library CLI application')
  .version('0.0.1');

program.addCommand(require('./author'));
program.addCommand(require('./book'));
program.addCommand(require('./bookRental'));


program.parse();