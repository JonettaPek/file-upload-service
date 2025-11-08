import { bootstrapApplication } from '@angular/platform-browser';
import { FileUploadComponent } from './app/components/file-upload/file-upload.component';
import { FileUploadModule } from './app/components/file-upload/file-upload.module';

bootstrapApplication(FileUploadComponent).catch((err) => console.error(err));
