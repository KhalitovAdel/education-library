import { Create, Datagrid, List, ReferenceField, ReferenceInput, SimpleForm, TextField, TextInput, required } from "react-admin";

export function BookCreate() {
    return <Create>
        <SimpleForm>
            <TextInput source="name" validate={[required()]} fullWidth label="Имя" />
            <ReferenceInput source="authorId" reference="authors" label="Автор" />
        </SimpleForm>
    </Create>
}

export function BookList() {
    return <List>
        <Datagrid>
            <TextField source="id" label="Идентификатор" />
            <TextField source="name" label="Имя" />
            <ReferenceField source="author.id" reference="authors" label="Автор" />
        </Datagrid>
    </List>
}