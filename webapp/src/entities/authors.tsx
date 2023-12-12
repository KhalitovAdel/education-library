import { Create, Datagrid, List, SimpleForm, TextField, TextInput, required } from "react-admin";

export function AuthorsCreate() {
    return <Create>
        <SimpleForm>
            <TextInput source="name" validate={[required()]} fullWidth label="Имя" />
        </SimpleForm>
    </Create>
}

export function AuthorList() {
    return <List>
        <Datagrid>
            <TextField source="id" label="Идентификатор" />
            <TextField source="name" label="Имя" />
        </Datagrid>
    </List>
}