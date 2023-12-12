/* eslint-disable no-undef */
const axios = require('axios');
const { Command } = require('commander');
const cfg = require('./cfg');

const program = new Command();

// Реализация контрола для связи автора и книги
program.name('book-rental');

program
    .command('create')
    .requiredOption('--bookId <bookId>', 'Ид книги')
    .requiredOption('--userName <userName>', 'Имя пользователя')
    .requiredOption('--endRentalDate <endRentalDate>', 'Дата окончания аренды')
    .action((opts) => {
        axios.request({
            url: cfg.baseUrl + '/book-rentals',
            method: 'POST',
            data: {
                userName: opts.userName,
                bookId: +opts.bookId,
                endRentalDate: opts.endRentalDate,
            }
        }).then(d => console.log(JSON.stringify(d.data)), console.error)
});

program
    .command('list')
    .action(() => {
        axios.request({
            url: cfg.baseUrl + '/book-rentals',
            method: 'GET',
        }).then(d => {
            d.data._embedded.books.forEach(author => console.log(JSON.stringify(author)));
        }, console.error)
});

module.exports = program