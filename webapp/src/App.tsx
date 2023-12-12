import {useState} from 'react';
import {
  Admin,
  Resource,
  ShowGuesser,
} from "react-admin";
import axios from "axios";
import { DataProviderImpl } from './data-provider';
import { AuthorList, AuthorsCreate } from './entities/authors';
import { BookList, BookCreate } from './entities/books';
import { BookRentalsCreate, BookRentalsList } from './entities/book-rentals';

// Точка входа в клиентское приложение
export function App() {
     const [http] = useState(() => axios.create({ baseURL: '/api/' }));

    return <Admin dataProvider={DataProviderImpl.getProvider(http)}>
        <Resource
             name="authors"
             recordRepresentation="name"
             create={AuthorsCreate}
             list={AuthorList}
             show={ShowGuesser}
        />
        <Resource
             name="book-rentals"
             create={BookRentalsCreate}
             list={BookRentalsList}
             show={ShowGuesser}
        />
        <Resource
             name="books"
             recordRepresentation="name"
             create={BookCreate}
             list={BookList}
             show={ShowGuesser}
        />
    </Admin>
}
