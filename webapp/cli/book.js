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
        }).then(d => console.log(JSON.stringify(d.data)), console.error)
});

program
    .command('list')
    .action(() => {
        axios.request({
            url: cfg.baseUrl + '/books',
            method: 'GET',
        }).then(d => {
            d.data._embedded.books.forEach(author => console.log(JSON.stringify(author)));
        }, console.error)
});

module.exports = program