import { Create, SimpleForm, TextInput, required, ReferenceInput, List, DateInput, DateField, Datagrid, TextField, ReferenceField } from "react-admin"

export function BookRentalsCreate() {
    return <Create>
        <SimpleForm>
            <ReferenceInput source="bookId" reference="books" label="Книга" />
            <TextInput source="userName" validate={[required()]} fullWidth label="Имя пользователя" />
            <DateInput source="endRentalDate" validate={[required()]} fullWidth label="Дата возврата" />
        </SimpleForm>
    </Create>
}

export function BookRentalsList() {
    return <List>
        <Datagrid>
            <TextField source="id" label="Идентификатор" />
            <TextField source="userName" label="Имя пользователя" />
            <ReferenceField source="book.id" reference="books" label="Книга" />
            <DateField source="endRentalDate" label="Дата возврата" />
        </Datagrid>
    </List>
}