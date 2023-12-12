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
        }).then(d => console.log(JSON.stringify(d.data)), console.error)
});

program
    .command('list')
    .action(() => {
        axios.request({
            url: cfg.baseUrl + '/authors',
            method: 'GET',
        }).then(d => {
            d.data._embedded.authors.forEach(author => console.log(JSON.stringify(author)));
        }, console.error)
});

module.exports = program