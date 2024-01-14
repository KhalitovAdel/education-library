/* eslint-disable no-undef */
const axios = require('axios');
const { Command } = require('commander');
const cfg = require('./cfg');

const program = new Command();

// Реализация контрола для автора
program.name('author');

program
    .command('create')
    .requiredOption('--name <name>', 'Имя')
    .action((opts) => {
        axios.request({
            url: cfg.baseUrl + '/authors',
            method: 'POST',
            data: {
                name: opts.name
            }
        }).then(d => console.log(JSON.stringify(d.data)), e => console.error(e.message))
});

program
    .command('list')
    .action(() => {
        axios.request({
            url: cfg.baseUrl + '/authors',
            method: 'GET',
        }).then(d => {
            d.data.forEach(author => console.log(JSON.stringify(author)));
        }, e => console.error(e.message))
});

module.exports = program