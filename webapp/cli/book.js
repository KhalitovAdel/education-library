/* eslint-disable no-undef */
const axios = require('axios');
const { Command } = require('commander');
const cfg = require('./cfg');

const program = new Command();

// Реализация контрола для книги
program.name('book');

program
    .command('create')
    .requiredOption('--authorId <authorId>', 'Ид автора')
    .requiredOption('--name <name>', 'Имя')
    .action((opts) => {
        axios.request({
            url: cfg.baseUrl + '/books',
            method: 'POST',
            data: {
                name: opts.name,
                authorId: +opts.authorId
            }
        }).then(d => console.log(JSON.stringify(d.data)), e => console.error(e.message))
});

program
    .command('list')
    .action(() => {
        axios.request({
            url: cfg.baseUrl + '/books',
            method: 'GET',
        }).then(d => {
            d.data.forEach(author => console.log(JSON.stringify(author)));
        }, e => console.error(e.message))
});

module.exports = program